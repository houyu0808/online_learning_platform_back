package com.houyu.online_learning_platform.back_stage_manage.controller;

import com.houyu.online_learning_platform.back_stage_manage.service.ClassService;
import com.houyu.online_learning_platform.back_stage_manage.vo.ClassVO;
import com.houyu.online_learning_platform.utils.responseMessage.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/class")
public class ClassController {
    @Autowired
    private ClassService classService;

    @GetMapping("/getclasspage")
    public ResponseMessage getClassList(@RequestParam String className, Pageable pageable){
        return ResponseMessage.ok(classService.getClassList(className,pageable));
    }
    @PostMapping("/saveclass")
    public  ResponseMessage saveClass(@RequestBody ClassVO classVO){
        return ResponseMessage.ok(classService.saveClass(classVO));
    }
    @GetMapping("/deleteclass")
    public ResponseMessage deleteClass(@RequestParam Integer id){
        return ResponseMessage.ok(classService.deleteClass(id));
    }
}
