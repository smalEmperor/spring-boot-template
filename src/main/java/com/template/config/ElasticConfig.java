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

        //新建host
        HttpHost httpHost = new HttpHost("172.16.20.74", 9200);

        //如ES设置了帐号密码，则需要提供登录令牌
        //final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        //credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(username,password));

        //创建client实例，host可以有多个，对应集群的情况
        RestClientBuilder builder = RestClient.builder(httpHost);

        //失败重试超时时间
        //builder.setMaxRetryTimeoutMillis(5 * 60 * 1000);

        // 异步httpclient连接延时配置
        builder.setRequestConfigCallback(requestConfigBuilder -> {
            //TODO 超时 设置 在 版本 7.x 才生效
            // 连接超时时间
            requestConfigBuilder.setConnectTimeout(30000);
            //更改客户端的超时限制默认30秒现在改为5分钟
            requestConfigBuilder.setSocketTimeout(300 * 1000);
            // 获取连接的超时时间
            requestConfigBuilder.setConnectionRequestTimeout(30000);
            return requestConfigBuilder;
        });

        // 异步httpclient连接数配置
        builder.setHttpClientConfigCallback(httpClientBuilder -> {
            //httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
            // 最大连接数
            httpClientBuilder.setMaxConnTotal(100);
            // 最大路由连接数
            httpClientBuilder.setMaxConnPerRoute(100);
            return httpClientBuilder;
        });

        return new RestHighLevelClient(builder);
    }

}
