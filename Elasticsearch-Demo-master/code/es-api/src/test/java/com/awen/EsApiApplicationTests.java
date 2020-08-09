package com.awen;

import com.alibaba.fastjson.JSON;
import com.awen.pojo.User;
import net.bytebuddy.asm.Advice;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.support.master.AcknowledgedRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

/**
 * es7.6.x 高级客户端测试 API
 */
@SpringBootTest
class EsApiApplicationTests {
//	@Autowired
//	@Qualifier("restHighLevelClient")
//	private RestHighLevelClient restHighLevelClient;
	@Autowired
	@Qualifier("restHighLevelClient")
	private RestHighLevelClient client;

	//测试索引的创建 Request  PUT  kuang_index
	@Test
	void testCreateIndex() throws IOException{
		//1、创建索引请求
		CreateIndexRequest request = new CreateIndexRequest("kuang_index");
		//2、客户端执行请求 indicesClient,请求后获得响应
		CreateIndexResponse createIndexResponse =
				client.indices().create(request, RequestOptions.DEFAULT);
		System.out.println(createIndexResponse);
	}
	//测试获取索引,判断是否存在
	@Test
	void testExistIndex() throws  IOException{
		GetIndexRequest request = new GetIndexRequest("kuang_index2");
		boolean exists = client.indices().exists(request, RequestOptions.DEFAULT);
		System.out.println(exists);
	}
	//测试删除索引
	@Test
	void testDeleteIndex() throws IOException{
		DeleteIndexRequest request = new DeleteIndexRequest("kuang_index");
		//删除
		AcknowledgedResponse delete =
				client.indices().delete(request,RequestOptions.DEFAULT);
		System.out.println(delete.isAcknowledged());
	}

	@Test
	void contextLoads() {
	}
	// 测试添加文档
	@Test
	void testAddDocument() throws IOException {
		//创建对象
//		User user = new User("柳小子", 30);
		User user = new User("狂神说", 3);
		//创建请求
		IndexRequest request = new IndexRequest("kuang_index");
		//规则 put /kuang_index/_doc/1
		request.id("1");
		request.timeout(TimeValue.timeValueSeconds(1));
		request.timeout("1s");

		//将我们的数据放入请求 json
		request.source(JSON.toJSONString(user), XContentType.JSON);

		//客户端发送请求 , 获取响应的结果
		IndexResponse indexResponse = client.index(request, RequestOptions.DEFAULT);
		System.out.println(indexResponse.toString());
		System.out.println(indexResponse.status());
	}
}
