package com.houyu.online_learning_platform.back_stage_manage.controller;

import com.houyu.online_learning_platform.back_stage_manage.service.TeacherService;
import com.houyu.online_learning_platform.back_stage_manage.vo.TeacherVO;
import com.houyu.online_learning_platform.utils.responseMessage.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    @GetMapping("/getteacherpage")
    public ResponseMessage getTeacherList(@RequestParam String username, Pageable pageable){
        return ResponseMessage.ok(teacherService.getTeacherList(username,pageable));
    }
    @PostMapping("/saveteacher")
    public ResponseMessage saveTeacher(@RequestBody TeacherVO teacherVO){
        String result = teacherService.saveTeacher(teacherVO);
        if(result.equals("更新成功") || result.equals("创建成功")){
            return ResponseMessage.ok(result);
        }else{
            return ResponseMessage.error(500,result);
        }
    }
    @GetMapping("/deleteteacher")
    public ResponseMessage deleteTeacher(@RequestParam Integer[] ids){
        return ResponseMessage.ok(teacherService.deleteTeacher(ids));
    }
    @GetMapping("getteacherbyid")
    public ResponseMessage getTeacherById(@RequestParam Integer id){
        return ResponseMessage.ok(teacherService.getTeacherById(id));
    }
}
