package com.template.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 全文检索Elasticsearch配置
 * @author df
 * @date 2019/9/7
 */

@Configuration
@AutoConfigureBefore(RedisConfig.class)
public class ElasticConfig {

    /**
     * 使用 restHighLevelClient 客户端
     * 连接的端口是 9200
     */
    @Bean(name = "restClient")
    public RestHighLevelClient restClient() {

        HttpHost httpHost = new HttpHost("172.16.20.74", 9200);

        RestClientBuilder builder = RestClient.builder(httpHost);
        //失败重试超时时间
        //builder.setMaxRetryTimeoutMillis(5 * 60 * 1000);

        // 异步httpclient连接延时配置
        builder.setRequestConfigCallback(requestConfigBuilder -> {
            //TODO 超时 设置 在 版本 7.x 才生效
            requestConfigBuilder.setConnectTimeout(30000);// 连接超时时间
            requestConfigBuilder.setSocketTimeout(300 * 1000);//更改客户端的超时限制默认30秒现在改为5分钟
            requestConfigBuilder.setConnectionRequestTimeout(30000);// 获取连接的超时时间
            return requestConfigBuilder;
        });

        // 异步httpclient连接数配置
        builder.setHttpClientConfigCallback(httpClientBuilder -> {
            httpClientBuilder.setMaxConnTotal(100);// 最大连接数
            httpClientBuilder.setMaxConnPerRoute(100);// 最大路由连接数
            return httpClientBuilder;
        });

        return new RestHighLevelClient(builder);
    }

}
