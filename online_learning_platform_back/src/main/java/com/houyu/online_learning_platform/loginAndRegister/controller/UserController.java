package com.houyu.online_learning_platform.loginAndRegister.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.houyu.online_learning_platform.back_stage_manage.dao.StudentRepository;
import com.houyu.online_learning_platform.back_stage_manage.dao.TeacherRepository;
import com.houyu.online_learning_platform.back_stage_manage.entity.Student;
import com.houyu.online_learning_platform.back_stage_manage.entity.Teacher;
import com.houyu.online_learning_platform.back_stage_manage.vo.StudentVO;
import com.houyu.online_learning_platform.back_stage_manage.vo.TeacherVO;
import com.houyu.online_learning_platform.loginAndRegister.dto.AdminDto;
import com.houyu.online_learning_platform.loginAndRegister.service.AdminLoginService;
import com.houyu.online_learning_platform.loginAndRegister.service.UserService;
import com.houyu.online_learning_platform.utils.loginStatus.LoginDTO;
import com.houyu.online_learning_platform.utils.loginStatus.LoginStatusUtils;
import com.houyu.online_learning_platform.utils.responseMessage.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Objects;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private AdminLoginService adminLoginService;
    @Autowired
    private UserService userService;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private TeacherRepository teacherRepository;

    //管理员登录
    @PostMapping("/adminlogin")
    public ResponseMessage adminLogin(@RequestBody AdminDto admindto) {
        String result = adminLoginService.loginVerification(admindto.getUsername(), admindto.getPassword());
        LoginDTO loginDTO = LoginStatusUtils.loginResponse(result);
        if(loginDTO.getCode() == 200 ){
            return ResponseMessage.ok(loginDTO.getResult(),loginDTO.getMessage());
        }else{
            return ResponseMessage.error(loginDTO.getCode(),loginDTO.getMessage());
        }
    }

    //用户登录
    @PostMapping("/userlogin")
    public ResponseMessage userLogin(@RequestBody AdminDto admindto) {
        String result = userService.userLogin(admindto.getUsername(), admindto.getPassword(), admindto.getIdentify());
        LoginDTO loginDTO = LoginStatusUtils.loginResponse(result);
        if(loginDTO.getCode() == 200 ){
            if(admindto.getIdentify().equals("学生")){
                Student student = studentRepository.findByUsername(result);
                student.setPassword("就不告诉你");
                return ResponseMessage.ok(student,loginDTO.getMessage());
            }else {
                Teacher teacher = teacherRepository.findByUsername(result);
                teacher.setPassword("就不告诉你");
                return ResponseMessage.ok(teacher,loginDTO.getMessage());
            }
        }else{
            return ResponseMessage.error(loginDTO.getCode(),loginDTO.getMessage());
        }
    }

    //学生注册
    @PostMapping("/studentregister")
    public ResponseMessage studentRegister(@RequestBody StudentVO studentVO) {
        String result = userService.studentRegister(studentVO);
        if(result.equals("注册成功")){
            return ResponseMessage.ok(result);
        } else {
            return ResponseMessage.error(500, result);
        }
    }

    //教师注册
    @PostMapping("/teacherregister")
    public ResponseMessage teacherRegister(@RequestBody TeacherVO teacherVO) {
        String result = userService.teacherRegister(teacherVO);
        if(result.equals("注册成功")){
            return ResponseMessage.ok(result);
        } else {
            return ResponseMessage.error(500, result);
        }
    }

    //token验证
    @PostMapping("/validatetoken")
    public ResponseMessage validateToken(@RequestParam String token) {
        DecodedJWT jwt = JWT.decode(token);
        Date expiresAt = jwt.getExpiresAt();
        if(expiresAt.after(new Date())){
            return ResponseMessage.ok(jwt.getClaim("identify").asString());
        }else{
            return ResponseMessage.error(500, "登陆过期，请重新登录");
        }
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
    //根据工号判断学生注册情况
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
    //修改密码
    @PostMapping("/changepassword")
    public ResponseMessage changePassword(@RequestBody AdminDto adminDto){
        String result = userService.changePassword(adminDto);
        if(result.equals("密码修改成功")){
            return ResponseMessage.ok(result);
        } else {
            return ResponseMessage.error(500, result);
        }
    }
}
