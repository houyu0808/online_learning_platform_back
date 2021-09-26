package com.houyu.online_learning_platform.utils.token;

import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class tokenUtils implements HandlerInterceptor {
    //拦截Request 校验token
    @Override
    public boolean preHandle(HttpServletRequest request,HttpServletResponse response,Object o) throws Exception{
        String token = request.getHeader("token");
        //token验证
        return true;
    }
}
