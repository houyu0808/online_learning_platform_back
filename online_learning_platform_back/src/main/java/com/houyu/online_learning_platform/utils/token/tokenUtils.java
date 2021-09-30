package com.houyu.online_learning_platform.utils.token;
;
import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.houyu.online_learning_platform.utils.responseMessage.ResponseMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@Configuration
public class tokenUtils implements HandlerInterceptor {

    @Value("${app.auther}")
    private String auther;
    //拦截Request 校验token
    @Override
    public boolean preHandle(HttpServletRequest request,HttpServletResponse response,Object o) throws Exception{
        String method = request.getMethod();
        String token = request.getHeader("token");
        if(HttpMethod.OPTIONS.toString().equals(method)){ //OPTIONS请求放行
            return true;
        }else{
            try{
                return decodeToken(token,response);
            }catch (Exception e){
                falseResult(response);
                return false;
            }
        }
    }
    //解析token
    public boolean decodeToken(String token,HttpServletResponse response) throws Exception{
        if(token == null){
            falseResult(response);
            return false;
        }else{
            try {
                DecodedJWT jwt = JWT.decode(token);
                Date nowTime = new Date();
                String type = jwt.getType();    //获取token类型
                String issuer = jwt.getIssuer();    //获取token发布者
                Date expiresAt = jwt.getExpiresAt(); //获取token过期时间
                if(issuer.equals("houyu")&&expiresAt.after(nowTime)){
                    return true;
                }else{
                    falseResult(response);
                    return false;
                }
            } catch (JWTDecodeException exception){
                //无效的 token
                falseResult(response);
                return false;
            }
        }
    }
    //返回错误信息
    public void falseResult(HttpServletResponse response) throws IOException{
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        ObjectMapper objectMapper = new ObjectMapper();
        response.getWriter().println(objectMapper.writeValueAsString(ResponseMessage.error("token验证失败!")));
    }
}
