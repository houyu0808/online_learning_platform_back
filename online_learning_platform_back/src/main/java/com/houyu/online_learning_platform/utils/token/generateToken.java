package com.houyu.online_learning_platform.utils.token;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.flywaydb.core.internal.util.DateUtils;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;

public class generateToken {
    public static final String SECRET = "JKKLJOoasdlfj";

    @Value(value = "${app.jwtExpirationAt}")
    private static int jwtExpirationAt;

//    生成token
    public static String createToken(String username, Integer id ,Date createTime){
        Date nowTime = new Date();
        Date expiryDate = new Date(nowTime.getTime() + 86400000);
        return JWT.create()
               .withIssuer("houyu")
               .withExpiresAt(expiryDate)
               .withClaim("username",username)
               .withClaim("id",id)
               .withIssuedAt(createTime)
               .sign(Algorithm.HMAC256(SECRET));
    }
}
