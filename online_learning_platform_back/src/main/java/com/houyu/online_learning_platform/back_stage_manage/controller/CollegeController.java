package com.houyu.online_learning_platform.back_stage_manage.controller;

import com.houyu.online_learning_platform.back_stage_manage.service.CollegeService;
import com.houyu.online_learning_platform.back_stage_manage.vo.CollegeVO;
import com.houyu.online_learning_platform.utils.responseMessage.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

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
        return ResponseMessage.ok(collegeService.saveCollege(collegeVO));
    }

    @GetMapping("/deletecollege")
    public ResponseMessage deleteCollege(@RequestParam Integer id){
        return ResponseMessage.ok(collegeService.deleteCollege(id));
    }
}
