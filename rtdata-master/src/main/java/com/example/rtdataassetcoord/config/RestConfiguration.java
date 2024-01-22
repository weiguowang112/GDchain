package com.example.rtdataassetcoord.config;

import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
public class RestConfiguration {

    // 基于OkHttpClient，注入自定义的RestTemplate
    @Bean
    public RestTemplate restTemplate(OkHttpClient okHttpClient) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setRequestFactory(new OkHttp3ClientHttpRequestFactory(okHttpClient));
        // 可以添加一些Interceptors，控制http请求的输入输出行为，比如增加身份认证、打印日志等
        // 自定义http请求发生错误时的处理器
        // restTemplate.setErrorHandler(new ContainerGroupErrorHandler());
        return restTemplate;
    }

    // 注入一个单例的OkHttpClient，设置一些通用的参数进行控制
    @Bean
    public OkHttpClient okHttpClient() {
        return new OkHttpClient.Builder()
                // 使用默认线程池，根据实际场景也可以自定义
                // .connectionPool(new ConnectionPool())
                // 连接超时
                .connectTimeout(Duration.ofSeconds(3))
                // 读超时
                .readTimeout(Duration.ofSeconds(25))
                // 写超时
                .writeTimeout(Duration.ofSeconds(3))
                // 连接超时重试一次
                .retryOnConnectionFailure(true)
                .build();
    }


}