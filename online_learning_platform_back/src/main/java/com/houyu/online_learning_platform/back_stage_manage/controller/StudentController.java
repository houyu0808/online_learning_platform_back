package com.houyu.online_learning_platform.back_stage_manage.controller;

import com.houyu.online_learning_platform.back_stage_manage.service.StudentService;
import com.houyu.online_learning_platform.back_stage_manage.vo.StudentVO;
import com.houyu.online_learning_platform.utils.responseMessage.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

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
        return ResponseMessage.ok(studentService.saveStudent(studentVO));
    }

    @GetMapping("/deletestudent")
    public ResponseMessage deleteStudent(Integer id){
        return ResponseMessage.ok(studentService.deleteStudent(id));
    }
}
