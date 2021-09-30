package com.houyu.online_learning_platform.back_stage_manage.controller;

import com.houyu.online_learning_platform.back_stage_manage.service.CollegeService;
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
    public ResponseMessage getCollegePage(Pageable pageable){
        return ResponseMessage.ok(collegeService.getCollegeList(pageable));
    }


}
