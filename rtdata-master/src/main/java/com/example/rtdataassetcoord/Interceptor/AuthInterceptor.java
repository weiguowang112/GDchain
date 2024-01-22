package com.example.rtdataassetcoord.Interceptor;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.rtdataassetcoord.FeignClient.AuthFeignClient;
import com.example.rtdataassetcoord.common.BusException;
import com.example.rtdataassetcoord.common.R;
import com.example.rtdataassetcoord.common.ThreadLocalUtil;
import com.example.rtdataassetcoord.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class AuthInterceptor implements HandlerInterceptor {
    @Autowired
    AuthFeignClient authFeignClient;

    public AuthInterceptor() {
    }

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String token = request.getHeader("token");
        if (token == null) {
            token = request.getParameter("token");
            if (token == null) {
                throw new BusException("缺少授权信息", HttpStatus.UNAUTHORIZED.value());
            }
        }

        R<User> resUserVo = this.authFeignClient.UserAuth();
        if (resUserVo.success) {
            ThreadLocalUtil.set("user", resUserVo.data);
            return true;
        } else {
            throw new BusException(resUserVo.message, HttpStatus.UNAUTHORIZED.value());
        }
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        ThreadLocalUtil.remove();
    }
}
