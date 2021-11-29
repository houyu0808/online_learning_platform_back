package com.houyu.online_learning_platform.back_stage_manage.controller;

import com.houyu.online_learning_platform.back_stage_manage.service.StudentService;
import com.houyu.online_learning_platform.back_stage_manage.vo.StudentVO;
import com.houyu.online_learning_platform.utils.responseMessage.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping("/getstudentpage")
    public ResponseMessage getStudentList(@RequestParam String username, Pageable pageable){
        return ResponseMessage.ok(studentService.getStudentList(username,pageable));
    }

    @PostMapping("/savestudent")
    public ResponseMessage saveStudent(@RequestBody StudentVO studentVO){
        String result = studentService.saveStudent(studentVO);
        if(result.equals("更新成功") || result.equals("创建成功")){
            return ResponseMessage.ok(result);
        }else{
            return ResponseMessage.error(500,result);
        }
    }

    @GetMapping("/deletestudent")
    public ResponseMessage deleteStudent(Integer[] ids){
        return ResponseMessage.ok(studentService.deleteStudent(ids));
    }
    @GetMapping("/getstudentbyid")
    public ResponseMessage getStudentById(Integer id){
        return ResponseMessage.ok(studentService.getStudentById(id));
    }
}
