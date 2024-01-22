package com.example.rtdataassetcoord.Interceptor;


import feign.RequestInterceptor;
import feign.RequestTemplate;
import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class FeignTokenInterceptor implements RequestInterceptor {
    public FeignTokenInterceptor() {
    }

    public void apply(RequestTemplate requestTemplate) {
        String token = "without token";
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        Enumeration headerNames = request.getHeaderNames();

        while(headerNames.hasMoreElements()) {
            String headerName = (String)headerNames.nextElement();
            String HeadValue = request.getHeader(headerName);
            if (headerName.equals("token")) {
                token = HeadValue;
            }
        }

        requestTemplate.header("token", new String[]{token});
    }
}
