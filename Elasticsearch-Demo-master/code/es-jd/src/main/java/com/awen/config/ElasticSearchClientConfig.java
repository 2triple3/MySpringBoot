package com.awen.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Liu Awen Email:willowawen@gmail.com
 * @create 2020-04-12 16:15
 */
//Spring步骤
//1、找对象
//2、放到spring中待用
//3、如果是SpringBoot就先分析源码
// xxxx  AutoConfiguation  xxxProperties
@Configuration  //xml - bean
public class ElasticSearchClientConfig {
    @Bean
    public RestHighLevelClient restHighLevelClient(){
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("192.168.1.100",9200,"http")));
        return client;
        //localhost
//        RestHighLevelClient client = new RestHighLevelClient(
//                RestClient.builder(
//                        new HttpHost("127.0.0.1",9200,"http")));
//        return client;


    }
}
