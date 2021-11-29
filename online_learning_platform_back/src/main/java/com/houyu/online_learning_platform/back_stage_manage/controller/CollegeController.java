package com.houyu.online_learning_platform.back_stage_manage.controller;

import com.houyu.online_learning_platform.back_stage_manage.service.CollegeService;
import com.houyu.online_learning_platform.back_stage_manage.vo.CollegeVO;
import com.houyu.online_learning_platform.utils.responseMessage.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/college")
public class CollegeController {

    @Autowired
    private CollegeService collegeService;

    @GetMapping("/getcollegepage")
    public ResponseMessage getCollegePage(@RequestParam String collegeName,Pageable pageable){
        return ResponseMessage.ok(collegeService.getCollegeList(collegeName,pageable));
    }

    @PostMapping("/savecollege")
    public ResponseMessage saveCollege(@RequestBody CollegeVO collegeVO){
        String result = collegeService.saveCollege(collegeVO);
        if(result.equals("更新成功") || result.equals("创建成功")){
            return ResponseMessage.ok(result);
        }else{
            return ResponseMessage.error(500,result);
        }
    }

    @GetMapping("/deletecollege")
    public ResponseMessage deleteCollege(@RequestParam Integer[] ids){
        return ResponseMessage.ok(collegeService.deleteCollege(ids));
    }

    @GetMapping("/getcollegebyid")
    public ResponseMessage getCollegeById(@RequestParam Integer id){
        return ResponseMessage.ok(collegeService.getCollegeById(id));
    }
}
