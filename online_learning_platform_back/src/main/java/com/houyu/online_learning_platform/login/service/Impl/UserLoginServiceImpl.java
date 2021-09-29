package com.houyu.online_learning_platform.login.service.Impl;

import com.houyu.online_learning_platform.login.dao.UserLoginRepository;
import com.houyu.online_learning_platform.login.entity.User;
import com.houyu.online_learning_platform.login.service.UserLoginService;
import com.houyu.online_learning_platform.utils.token.generateToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class UserLoginServiceImpl implements UserLoginService{

    @Autowired
    private UserLoginRepository userLoginRepository;
    @Autowired
    private HttpServletResponse httpServletResponse;

    @Override
    public  String loginVerification(String username, String password){
        User user =  userLoginRepository.findByUsername(username);
        Date createTime = Calendar.getInstance().getTime();
        if(user != null){
            if(user.getPassword().equals(password)){
                String jwt_token = generateToken.createToken(username,user.getId(),createTime);
                httpServletResponse.addHeader("jwt-token",jwt_token);
                return "登录成功";
            }else{
                return "密码错误";
            }
        }else{
            return "用户名不存在!请重新输入！";
        }
    }
}
