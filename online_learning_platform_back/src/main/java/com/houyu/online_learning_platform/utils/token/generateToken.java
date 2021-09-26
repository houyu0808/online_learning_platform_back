package com.houyu.online_learning_platform.utils.token;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Date;

public class generateToken {
    public static final String SECRET = "JKKLJOoasdlfj";

//    生成token
    public static String createToken(String username, Integer id ,Date createTime){
       return JWT.create().withClaim("username",username)
               .withClaim("id",id)
               .withClaim("createTime",createTime)
               .sign(Algorithm.HMAC256(SECRET));
    }
}
