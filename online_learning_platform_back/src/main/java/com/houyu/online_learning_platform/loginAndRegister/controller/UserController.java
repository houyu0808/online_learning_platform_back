package com.houyu.online_learning_platform.loginAndRegister.controller;

import com.houyu.online_learning_platform.back_stage_manage.vo.StudentVO;
import com.houyu.online_learning_platform.back_stage_manage.vo.TeacherVO;
import com.houyu.online_learning_platform.loginAndRegister.service.AdminLoginService;
import com.houyu.online_learning_platform.loginAndRegister.service.UserService;
import com.houyu.online_learning_platform.utils.responseMessage.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private AdminLoginService adminLoginService;
    @Autowired
    private UserService userService;

    @PostMapping("/adminlogin")
    public ResponseMessage adminLogin(@RequestParam String username, @RequestParam String password){
        return ResponseMessage.ok(adminLoginService.loginVerification(username, password));
    }

    @PostMapping("/userlogin")
    public ResponseMessage userLogin(@RequestParam String username, @RequestParam String password, @RequestParam String identify){
        return ResponseMessage.ok(userService.userLogin(username,password,identify));
    }

    @PostMapping("/studentregister")
    public ResponseMessage studentRegister(@RequestBody StudentVO studentVO){
        return ResponseMessage.ok(userService.studentRegister(studentVO));
    }

    @PostMapping("/teacherregister")
    public ResponseMessage teacherRegister(@RequestBody TeacherVO teacherVO){
        return ResponseMessage.ok(userService.teacherRegister(teacherVO));
    }
}
