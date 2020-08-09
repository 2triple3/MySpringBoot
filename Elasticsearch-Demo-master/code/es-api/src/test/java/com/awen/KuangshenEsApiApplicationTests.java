package com.awen;

import com.alibaba.fastjson.JSON;
import com.awen.pojo.User;
//import com.awen.utils.ESconst;
import com.awen.utils.ESconst;
import com.sun.corba.se.spi.activation.ServerAlreadyRegistered;
import lombok.SneakyThrows;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchPhase;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import sun.reflect.ReflectionFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;

/**
 * 狂神讲解 es7.6.x 高级客户端测试 API
 */
@SpringBootTest
class KuangshenEsApiApplicationTests {
    // 面向对象来操作
    @Autowired
    @Qualifier("restHighLevelClient")
    private RestHighLevelClient client;

    // 测试索引的创建 Request PUT kuang_index
    @Test
    void testCreateIndex() throws IOException {
// 1、创建索引请求
        CreateIndexRequest request = new CreateIndexRequest("kuang_index");
// 2、客户端执行请求 IndicesClient,请求后获得响应
        CreateIndexResponse createIndexResponse =
                client.indices().create(request, RequestOptions.DEFAULT);
        System.out.println(createIndexResponse);
    }

    // 测试获取索引,判断其是否存在
    @Test
    void testExistIndex() throws IOException {
        GetIndexRequest request = new GetIndexRequest("kuang_index2");
        boolean exists = client.indices().exists(request,
                RequestOptions.DEFAULT);
        System.out.println(exists);
    }

    // 测试删除索引
    @Test
    void testDeleteIndex() throws IOException {
        DeleteIndexRequest request = new DeleteIndexRequest("kuang_index");
// 删除
        AcknowledgedResponse delete = client.indices().delete(request,
                RequestOptions.DEFAULT);
        System.out.println(delete.isAcknowledged());
    }

    // 测试添加文档
    @Test
    void testAddDocument() throws IOException {
        // 创建对象
        User user = new User("狂神说", 3);
        // 创建请求
        IndexRequest request = new IndexRequest("kuang_index");
        // 规则 put /kuang_index/_doc/1
        request.id("1");
        request.timeout(TimeValue.timeValueSeconds(1));
        request.timeout("1s");
        // 将我们的数据放入请求 json  fastjson依赖导入
        request.source(JSON.toJSONString(user), XContentType.JSON);
        //对象变JOSN   插入  放入  获取
        // 客户端发送请求 , 获取响应的结果
        IndexResponse indexResponse = client.index(request,
                RequestOptions.DEFAULT);
        //拿到了响应的结果   我们来显示
        System.out.println(indexResponse.toString()); //
        System.out.println(indexResponse.status()); // 对应我们命令返回的状态 CREATED
    }

    // 获取文档，判断是否存在 get /index/doc/1
    @Test
    void testIsExists() throws IOException {
        GetRequest getRequest = new GetRequest("kuang_index", "1");
        // 不获取返回的 _source 的上下文了  效率高点
        // .方法  测试  IO异常找不到
        getRequest.fetchSourceContext(new FetchSourceContext(false));
        getRequest.storedFields("_none_");
        boolean exists = client.exists(getRequest, RequestOptions.DEFAULT);
        System.out.println(exists);
    }

    // 获得文档的信息
    @Test
    void testGetDocument() throws IOException {
        GetRequest getRequest = new GetRequest("kuang_index", "1");
        GetResponse getResponse = client.get(getRequest,
                RequestOptions.DEFAULT);
        System.out.println(getResponse.getSourceAsString()); // 打印文档的内容
        System.out.println(getResponse); // 返回的全部内容和命令式一样的
        //{"age":3,"name":"狂神说"}  键值的存在
        //{"_index":"kuang_index","_type":"_doc","_id":"1","_version":1,"_seq_no":0,"_primary_term":1,"found":true,"_source":{"age":3,"name":"狂神说"}}
    }

    // 更新文档的信息
    @Test
    void testUpdateRequest() throws IOException {
        UpdateRequest updateRequest = new UpdateRequest("kuang_index", "1");
        updateRequest.timeout("1s");
        User user = new User("狂神说Java", 18);
        //XContentType 告诉什么类型
        updateRequest.doc(JSON.toJSONString(user), XContentType.JSON);
        UpdateResponse updateResponse = client.update(updateRequest,
                RequestOptions.DEFAULT);
        System.out.println(updateResponse.status());
    }

    // 删除文档记录
    @Test
    void testDeleteRequest() throws IOException {
        DeleteRequest request = new DeleteRequest("kuang_index", "1");
        request.timeout("1s");
        DeleteResponse deleteResponse = client.delete(request,
                RequestOptions.DEFAULT);
        System.out.println(deleteResponse.status());
    }
    //大量的数据   BulkRequest  Bulk n. 体积，容量；大多数，大部分；大块
    // 特殊的，真的项目一般都会批量插入数据！
    @Test
    void testBulkRequest() throws IOException {
        BulkRequest bulkRequest = new BulkRequest();
        bulkRequest.timeout("10s");
        ArrayList<User> userList = new ArrayList<>();
        userList.add(new User("kuangshen1", 3));
        userList.add(new User("kuangshen2", 3));
        userList.add(new User("kuangshen3", 3));
        userList.add(new User("qinjiang1", 3));
        userList.add(new User("qinjiang1", 3));
        userList.add(new User("qinjiang1", 3));
        // 批处理请求
        for (int i = 0; i < userList.size(); i++) {
            // 批量更新和批量删除，就在这里修改对应的请求就可以了
            bulkRequest.add(
                    new IndexRequest("kuang_index")
                            .id("" + (i + 1))
                            .source(JSON.toJSONString(userList.get(i)), XContentType.JSON));
        }
        BulkResponse bulkResponse = client.bulk(bulkRequest,
                RequestOptions.DEFAULT);
        System.out.println(bulkResponse.hasFailures()); // 是否失败，返回 false 代表成功！
    }

    // 查询
// SearchRequest 搜索请求
// SearchSourceBuilder 条件构造
// HighlightBuilder 构建高亮
// TermQueryBuilder 精确查询
// MatchAllQueryBuilder
// xxx QueryBuilder 对应我们刚才看到的命令！
    //各种查询
    @Test
    void testSearch() throws IOException {
        //真实传递常量
//        SearchRequest searchRequest = new SearchRequest("kuang_index");
        SearchRequest searchRequest = new SearchRequest(ESconst.ES_INDEX);
// 构建搜索条件
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        //设置高亮
        sourceBuilder.highlighter();
// 查询条件，我们可以使用 QueryBuilders 工具来实现
// QueryBuilders.termQuery 精确
// QueryBuilders.matchAllQuery() 匹配所有
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("name",
                "qinjiang1");
// MatchAllQueryBuilder matchAllQueryBuilder =
        QueryBuilders.matchAllQuery();
        sourceBuilder.query(termQueryBuilder);
        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
        searchRequest.source(sourceBuilder);
        SearchResponse searchResponse = client.search(searchRequest,
                RequestOptions.DEFAULT);
        System.out.println(JSON.toJSONString(searchResponse.getHits()));
        System.out.println("=================================");
        for (SearchHit documentFields : searchResponse.getHits().getHits()) {
            System.out.println(documentFields.getSourceAsMap());
        }
    }
}