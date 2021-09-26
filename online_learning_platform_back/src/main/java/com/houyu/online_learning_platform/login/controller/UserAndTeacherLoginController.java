package com.houyu.online_learning_platform.login.controller;

import com.houyu.online_learning_platform.login.service.UserLoginService;
import com.houyu.online_learning_platform.utils.responseMessage.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/login")
public class UserAndTeacherLoginController {
    @Autowired
    private UserLoginService userLoginService;

    @GetMapping("/studentLogin")
    public ResponseMessage studentLogin(@RequestParam String username, @RequestParam String password){
        return ResponseMessage.ok(userLoginService.loginVerification(username, password));
    }
}
