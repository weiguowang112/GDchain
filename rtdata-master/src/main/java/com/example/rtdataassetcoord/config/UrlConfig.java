package com.example.rtdataassetcoord.config;

import com.example.rtdataassetcoord.Interceptor.AuthInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class UrlConfig implements WebMvcConfigurer {

    public static final String[] excludeSwagger={"/swagger-ui/**","/swagger-resources/**","/v3/**"};

    public static final String[] excludeUrl={"/api/rtdataassetcoord/auth/**","/api/rtdataassetcoord/file/uploadHeadImg"};

    /**
     * 授权拦截器，通过请求中的token，验证授权信息，授权正确则把token对应的user信息放在ThreadLocalUtil中，获取用户信息示例：UserVo userVo = ThreadLocalUtil.get("user");
     * @return
     */
    @Bean
    public AuthInterceptor getAuthInterceptor()
    {
        return new AuthInterceptor();
    }

//    /**
//     * 日志拦截器，请求完成后，会将请求信息保存在日志模块;
//     * @return
//     */
//    @Bean
//    public LogInterceptor getLogInterceptor()
//    {
//        return new LogInterceptor();
//    }

    @Override
    public void addInterceptors(InterceptorRegistry registry)
    {
        registry.addInterceptor(getAuthInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns(excludeSwagger)
                .excludePathPatterns(excludeUrl)
                .order(-10);
//        registry.addInterceptor(getLogInterceptor())
//                .addPathPatterns("/**")
//                .excludePathPatterns(excludeSwagger)
//                .excludePathPatterns(excludeUrl)
//                .order(-10);
    }
}
