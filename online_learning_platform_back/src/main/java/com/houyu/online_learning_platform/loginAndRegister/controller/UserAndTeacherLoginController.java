package com.houyu.online_learning_platform.loginAndRegister.controller;

import com.houyu.online_learning_platform.loginAndRegister.service.UserLoginService;
import com.houyu.online_learning_platform.utils.responseMessage.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class UserAndTeacherLoginController {
    @Autowired
    private UserLoginService userLoginService;

    @PostMapping("/login")
    public ResponseMessage userLogin(@RequestParam String username, @RequestParam String password){
        return ResponseMessage.ok(userLoginService.loginVerification(username, password));
    }
//    @PostMapping("/register")
//    public ResponseMessage userRegister(){
//
//    }
}
