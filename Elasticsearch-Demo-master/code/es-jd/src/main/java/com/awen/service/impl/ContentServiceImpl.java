package com.awen.service.impl;

import com.alibaba.fastjson.JSON;
import com.awen.pojo.Content;
import com.awen.service.IContentService;
import com.awen.utils.HtmlParseUtil;
import org.apache.lucene.util.QueryBuilder;
import org.elasticsearch.action.DocWriteRequest;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.directory.SearchResult;
import javax.swing.text.Highlighter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author Liu Awen Email:willowawen@gmail.com
 * @create 2020-04-13 0:17
 */
//  业务编写
//  连撸几个小时  不能断的  一旦断了没有那种感觉你们知道吗？
//  不能断  连撸几个小时  或者连听几个小时   连讲几个小时的  最喜欢这样了 哈哈
@Service
public class ContentServiceImpl implements IContentService {
    @Autowired
    private RestHighLevelClient restHighLevelClient;
    //Exception in thread "main" java.lang.NullPointerException
    //	at com.awen.service.impl.ContentServiceImpl.parseContent(ContentServiceImpl.java:50)
    //	at com.awen.service.impl.ContentServiceImpl.main(ContentServiceImpl.java:33)

    // 不能直接使用 @Autowired 只要Spring容器 写到controller
//    public static void main(String[] args) throws Exception{
//        //测试下  调用这个方法
//        Boolean java = new ContentServiceImpl().parseContent("java");
//    }
    //1、解析数据放入ES索引中
    public Boolean parseContent(String keywords) throws Exception {
        List<Content> contents = new HtmlParseUtil().parseJD(keywords);
        //把查询到的数据放入es中
        BulkRequest bulkRequest = new BulkRequest();
        bulkRequest.timeout("2m");
//        bulkRequest.timeout("10m");
//        IndexRequest request = new IndexRequest("jd_goods");
//        fori 快捷写喜欢   回车


        DeleteIndexRequest request = new DeleteIndexRequest("jd_goods");
        // 删除
        AcknowledgedResponse delete = restHighLevelClient.indices().delete(request,
                RequestOptions.DEFAULT);
        System.out.println(delete.isAcknowledged());


        for (int i = 0; i < contents.size(); i++) {
            //输出一下 看下那里错了
//            System.out.println(JSON.toJSONString(contents.get(i)));
            //去页面创建个索引 jd_goods 可以手动创建 也可以代码创建 之前有
            //JSON   导入依赖 fastjson

//            bulkRequest.add(
//                    new IndexRequest("jd_goods")
//                    .source(JSON.toJSONString(contents.get(i)), XContentType.JSON));
            // 批量更新和批量删除，就在这里修改对应的请求就可以了
            //可以叠加了 嗯嗯 还是没用的

            bulkRequest.add(
                    new IndexRequest("jd_goods")
                            .id("" + (i + 1))
                            .source(JSON.toJSONString(contents.get(i)), XContentType.JSON));
        }
        //知道了  索引要是已经创建了IndexRequest  再运行创建会出错的  那怎么办呢？
        //new IndexRequest("jd_goods")
        BulkResponse bulk = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
        //搞定收工
        System.out.println(bulk.hasFailures());
        return !bulk.hasFailures();
    }

    //2、获取这些数据实现搜索功能
    //问我 为什么头脑这么清晰 晚上少做点事就行的
    public List<Map<String,Object>> searchPage(String keyword, int pageNo, int pageSize) throws Exception{
        if (pageNo<=1){
            pageNo = 1;
        }
        // 条件搜索
        SearchRequest searchRequest = new SearchRequest("jd_goods"); //索引
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();

        //分页
        sourceBuilder.from(pageNo);
        sourceBuilder.size(pageSize);
        //精准匹配
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("title", keyword);
        sourceBuilder.query(termQueryBuilder);
//        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
        sourceBuilder.timeout(new TimeValue(600, TimeUnit.SECONDS));

        // 执行搜索
        searchRequest.source(sourceBuilder);
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        //解析结果
        //写代码逻辑清晰   讲课 讲了这么久  看什么事情都清晰
        ArrayList<Map<String,Object>> list = new ArrayList<>();
        for (SearchHit documentFields : searchResponse.getHits().getHits()) {
            list.add(documentFields.getSourceAsMap());
        }
        return  list;
    }
    //3、获取这些数据实现搜索功能  搜索高亮功能
    //问我 为什么头脑这么清晰 晚上少做点事就行的
    public List<Map<String,Object>> searchPageHighlight(String keyword, int pageNo, int pageSize) throws Exception{
        if (pageNo<=1){
            pageNo = 1;
        }
        // 条件搜索
        SearchRequest searchRequest = new SearchRequest("jd_goods"); //索引
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();

        //分页
        sourceBuilder.from(pageNo);
        sourceBuilder.size(pageSize);
        //精准匹配
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("title", keyword);
        sourceBuilder.query(termQueryBuilder);
//        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
        sourceBuilder.timeout(new TimeValue(600, TimeUnit.SECONDS));
        //高亮
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.field("title");
        highlightBuilder.requireFieldMatch(false);//多个高亮显示 多个高亮关闭
        highlightBuilder.preTags("<span style='color:red'>");
        highlightBuilder.postTags("</span>");
        sourceBuilder.highlighter(highlightBuilder);

        // 执行搜索
        searchRequest.source(sourceBuilder);
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        //解析结果
        //写代码逻辑清晰   讲课 讲了这么久  看什么事情都清晰
        ArrayList<Map<String,Object>> list = new ArrayList<>();
        for (SearchHit hit : searchResponse.getHits().getHits()) {
            //1、获取高亮的字段
            Map<String, HighlightField> highlightFields = hit.getHighlightFields();
            HighlightField title = highlightFields.get("title");
            Map<String, Object> sourceAsMap = hit.getSourceAsMap();//原来的结果
            //解析高亮的字段
            //将原来的字段换为我们高亮的字段即可！
            if (title != null){
                Text[] fragments = title.fragments();
                String n_title = "";
                for (Text text:fragments){
                    n_title += text;
                }
//                sourceAsMap.put("title", "n_title");//高亮字段替换掉原来的内容即可！
                //"n_title"错误
                sourceAsMap.put("title", n_title);//高亮字段替换掉原来的内容即可！
            }
            list.add(sourceAsMap);
        }
        return  list;
    }
}
