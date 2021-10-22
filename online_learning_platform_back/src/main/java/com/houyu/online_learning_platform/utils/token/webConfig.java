package com.houyu.online_learning_platform.utils.token;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class webConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //需要拦截的路径，/**表示需要拦截所有请求
        String[] addPathPatterns={
                "/student/savestudent"
        };
        //不需要拦截的路径
        String [] excludePathPatterns={

        };
        //注册一个登录拦截器
        registry.addInterceptor(new tokenUtils())
                .addPathPatterns(addPathPatterns)
                .excludePathPatterns(excludePathPatterns);
        //注册一个权限拦截器  如果有多个拦截器 ，只需要添加以下一行代码
        //registry.addInterceptor(new tokenUtils())
        // .addPathPatterns(addPathPatterns)
        // .excludePathPatterns(excludePathPatterns);

    }
}
