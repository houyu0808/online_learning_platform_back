package com.houyu.online_learning_platform.utils.token;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.flywaydb.core.internal.util.DateUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;

@Component
public class GenerateToken {
    public static final String SECRET = "JKKLJOoasdlfj";

    @Value(value = "${app.jwtExpirationAt}")
    private int jwtExpirationAt;
    @Value(value = "${app.secret}")
    private String secret;
    @Value("${app.auther}")
    private String auther;

    private static int jwtExpirationAtTmp;
    private static String secretTmp;
    private static String autherTmp;

//    生成token
    public static String createToken(String username, Integer id,String identify){
        Date nowTime = new Date();
        Date expiryDate = new Date(nowTime.getTime() + jwtExpirationAtTmp);
        return JWT.create()
               .withIssuer(autherTmp)
               .withIssuedAt(new Date())
               .withExpiresAt(expiryDate)
                .withClaim("identify", identify)
               .withClaim("username",username)
               .withClaim("id",id)
               .sign(Algorithm.HMAC256(secretTmp));
    }
    @PostConstruct
    public void init() {
        jwtExpirationAtTmp = this.jwtExpirationAt;
        secretTmp = this.secret;
        autherTmp = this.auther;
    }

}
