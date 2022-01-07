package com.houyu.online_learning_platform.loginAndRegister.service.Impl;

import com.houyu.online_learning_platform.loginAndRegister.dao.AdminLoginRepository;
import com.houyu.online_learning_platform.loginAndRegister.entity.Admin;
import com.houyu.online_learning_platform.loginAndRegister.service.AdminLoginService;
import com.houyu.online_learning_platform.utils.token.GenerateToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

@Service
@Transactional
public class AdminLoginServiceImpl implements AdminLoginService {

    @Autowired
    private AdminLoginRepository userLoginRepository;
    @Autowired
    private HttpServletResponse httpServletResponse;

    @Override
    public String loginVerification(String username, String password){
        Admin user =  userLoginRepository.findByUsername(username);
        if(user != null){
            if(user.getPassword().equals(password)){
                String jwt_token = GenerateToken.createToken(username,user.getId(),"管理员");
                httpServletResponse.addHeader("token",jwt_token);
                httpServletResponse.addHeader("Access-Control-Expose-Headers", "token");
                return "超级管理员";
            }else{
                return "1";
            }
        }else{
            return "2";
        }
    }
}
