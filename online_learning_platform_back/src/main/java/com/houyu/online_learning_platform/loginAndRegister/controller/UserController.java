package com.houyu.online_learning_platform.loginAndRegister.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.houyu.online_learning_platform.back_stage_manage.vo.StudentVO;
import com.houyu.online_learning_platform.back_stage_manage.vo.TeacherVO;
import com.houyu.online_learning_platform.loginAndRegister.dto.AdminDto;
import com.houyu.online_learning_platform.loginAndRegister.service.AdminLoginService;
import com.houyu.online_learning_platform.loginAndRegister.service.UserService;
import com.houyu.online_learning_platform.utils.responseMessage.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private AdminLoginService adminLoginService;
    @Autowired
    private UserService userService;

    @PostMapping("/adminlogin")
    public ResponseMessage adminLogin(@RequestBody AdminDto admindto){
        String result = adminLoginService.loginVerification(admindto.getUsername(), admindto.getPassword());
        if(result.equals("登陆成功")){
            return ResponseMessage.ok(result);
        }else{
            return ResponseMessage.error(500,result);
        }
    }

    @PostMapping("/userlogin")
    public ResponseMessage userLogin(@RequestBody AdminDto admindto){
        String result = userService.userLogin(admindto.getUsername(),admindto.getPassword(), admindto.getIdentify());
        if(result.equals("登陆成功")){
            return ResponseMessage.ok(result);
        }else{
            return ResponseMessage.error(500,result);
        }

    }

    @PostMapping("/studentregister")
    public ResponseMessage studentRegister(@RequestBody StudentVO studentVO){
        return ResponseMessage.ok(userService.studentRegister(studentVO));
    }

    @PostMapping("/teacherregister")
    public ResponseMessage teacherRegister(@RequestBody TeacherVO teacherVO){
        return ResponseMessage.ok(userService.teacherRegister(teacherVO));
    }

    @PostMapping("/validatetoken")
    public ResponseMessage validateToken(@RequestParam String token){
        DecodedJWT jwt = JWT.decode(token);
        Date expiresAt = jwt.getExpiresAt();
        return ResponseMessage.ok(expiresAt.after(new Date()));
    }
}
