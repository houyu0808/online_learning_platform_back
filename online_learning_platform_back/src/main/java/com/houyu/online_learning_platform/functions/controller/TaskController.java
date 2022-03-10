package com.houyu.online_learning_platform.functions.controller;

import com.houyu.online_learning_platform.functions.service.TaskService;
import com.houyu.online_learning_platform.functions.vo.TaskVO;
import com.houyu.online_learning_platform.utils.responseMessage.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/task")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @PostMapping("/addtask")
    public ResponseMessage addTask(@RequestParam("file") MultipartFile file,TaskVO taskVO){
        taskService.addTask(file, taskVO);
        return ResponseMessage.ok(200,"发布成功");
    }
    @GetMapping("/deletetask")
    public ResponseMessage deleteTask(@RequestParam Integer id){
        taskService.deleteTask(id);
        return ResponseMessage.ok(200,"删除成功");
    }

    @GetMapping("/gettaskpage")
    public ResponseMessage getTaskPage(@RequestParam String teacherCode, Pageable pageable){
        return ResponseMessage.ok(taskService.getTeacherTaskPage(teacherCode,pageable));
    }
}
