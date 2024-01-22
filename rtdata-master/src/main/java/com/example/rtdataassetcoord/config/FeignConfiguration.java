package com.example.rtdataassetcoord.config;

import com.example.rtdataassetcoord.Interceptor.FeignTokenInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfiguration {

    /**
     * FeignTokenInterceptor作用：调用FeignClient时，会把当前请求的token放到FeignClient的请求头中
     * @return
     */
    @Bean
    public FeignTokenInterceptor basicAuthRequestInterceptor() {
        return new FeignTokenInterceptor();
    }
}
