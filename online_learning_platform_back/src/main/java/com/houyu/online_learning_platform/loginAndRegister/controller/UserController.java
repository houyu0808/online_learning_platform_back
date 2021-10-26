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

    //管理员登录
    @PostMapping("/adminlogin")
    public ResponseMessage adminLogin(@RequestBody AdminDto admindto) {
        String result = adminLoginService.loginVerification(admindto.getUsername(), admindto.getPassword());
        if (result.equals("登陆成功")) {
            return ResponseMessage.ok(result);
        } else {
            return ResponseMessage.error(500, result);
        }
    }

    //用户登录
    @PostMapping("/userlogin")
    public ResponseMessage userLogin(@RequestBody AdminDto admindto) {
        String result = userService.userLogin(admindto.getUsername(), admindto.getPassword(), admindto.getIdentify());
        if (result.equals("登陆成功")) {
            return ResponseMessage.ok(result);
        } else {
            return ResponseMessage.error(500, result);
        }

    }

    //学生注册
    @PostMapping("/studentregister")
    public ResponseMessage studentRegister(@RequestBody StudentVO studentVO) {
        return ResponseMessage.ok(userService.studentRegister(studentVO));
    }

    //教师注册
    @PostMapping("/teacherregister")
    public ResponseMessage teacherRegister(@RequestBody TeacherVO teacherVO) {
        return ResponseMessage.ok(userService.teacherRegister(teacherVO));
    }

    //token验证
    @PostMapping("/validatetoken")
    public ResponseMessage validateToken(@RequestParam String token) {
        DecodedJWT jwt = JWT.decode(token);
        Date expiresAt = jwt.getExpiresAt();
        return ResponseMessage.ok(expiresAt.after(new Date()));
    }

    //根据学号判断学生注册情况
    @GetMapping("/searchstudent")
    public ResponseMessage searchStudent(@RequestParam String stuNumber) {
        String student = userService.searchStudent(stuNumber);
        switch (student) {
            case "1":
                return ResponseMessage.error(500, "请输入学号");
            case "2":
                return ResponseMessage.error(500, "请输入有效学号");
            case "3":
                return ResponseMessage.error(500, "当前学号已被注册，需要重置请联系管理员");
            default:
                return ResponseMessage.ok(student);

        }
    }

    @GetMapping("/searchteacher")
    public ResponseMessage searchTeacher(@RequestParam String employeeNumber) {
        String teacher = userService.searchTeacher(employeeNumber);
        switch (teacher) {
            case "1":
                return ResponseMessage.error(500, "请输入工号");
            case "2":
                return ResponseMessage.error(500, "请输入有效工号");
            case "3":
                return ResponseMessage.error(500, "当前工号已被注册，需要重置请联系管理员");
            default:
                return ResponseMessage.ok(teacher);
        }
    }
}
