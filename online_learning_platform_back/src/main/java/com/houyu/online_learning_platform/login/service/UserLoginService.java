package com.houyu.online_learning_platform.login.service;

import com.houyu.online_learning_platform.login.vo.UserVO;

import java.util.List;

public interface UserLoginService {
    String loginVerification(String username, String password);
}
