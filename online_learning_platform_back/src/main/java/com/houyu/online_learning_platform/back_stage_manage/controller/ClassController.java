package com.houyu.online_learning_platform.back_stage_manage.controller;

import com.houyu.online_learning_platform.back_stage_manage.service.ClassService;
import com.houyu.online_learning_platform.back_stage_manage.vo.ClassVO;
import com.houyu.online_learning_platform.utils.responseMessage.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

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
        String result = classService.saveClass(classVO);
        if(result.equals("更新成功") || result.equals("创建成功")){
            return ResponseMessage.ok(result);
        }else{
            return ResponseMessage.error(500,result);
        }
    }
    @GetMapping("/deleteclass")
    public ResponseMessage deleteClass(@RequestParam Integer[] ids){
        return ResponseMessage.ok(classService.deleteClass(ids));
    }
    @GetMapping("/getclassbyid")
    public  ResponseMessage getClassById(@RequestParam Integer id){
        return ResponseMessage.ok(classService.getClassById(id));
    }
    @GetMapping("/getclassbymajor")
    public ResponseMessage getClassByMajor(@RequestParam BigInteger majorCode){
        return ResponseMessage.ok(classService.getClassByMajor(majorCode));
    }
    @GetMapping("/getclassbycollege")
    public ResponseMessage getClassList(@RequestParam BigInteger collegeCode){
        return ResponseMessage.ok(classService.getClassByCollege(collegeCode));
    }
}
