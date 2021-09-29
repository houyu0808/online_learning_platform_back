package com.houyu.online_learning_platform.utils.token;
;
import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.houyu.online_learning_platform.utils.responseMessage.ResponseMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Configuration
public class tokenUtils implements HandlerInterceptor {

    public static final String SECRET = "JKKLJOoasdlfj";
    @Value(value = "${app.secret}")
    private String secret;
    @Value(value = "${app.auther}")
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
                return decodeToken(token);
            }catch (Exception e){
                return false;
            }
        }
    }
    //解析token
    public boolean decodeToken(String token){
        if(token == null){
            return false;
        }else{
            try {
                DecodedJWT jwt = JWT.decode(token);

                String algorithm = jwt.getAlgorithm(); //获取算法类型
                String type = jwt.getType();    //获取token类型
                String issuer = jwt.getIssuer();    //获取token发布者
                Date expiresAt = jwt.getExpiresAt(); //获取token过期时间
                Date issuedAt = jwt.getIssuedAt();  // 获取token生产日期

                System.out.println(algorithm);  //=>    HS256
                System.out.println(type);       //=>    JWT
                System.out.println(issuer);     //=>    auth0
                System.out.println(expiresAt);  //=>    Sat Jan 11 22:25:13 CST 2020
                System.out.println(issuedAt);   //=>    Sat Jan 11 20:25:13 CST 2020
                return true;
            } catch (JWTDecodeException exception){
                //无效的 token
                return false;
            }
        }
    }
}
