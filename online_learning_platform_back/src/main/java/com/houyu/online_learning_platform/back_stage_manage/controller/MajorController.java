package com.houyu.online_learning_platform.back_stage_manage.controller;

import com.houyu.online_learning_platform.back_stage_manage.entity.Major;
import com.houyu.online_learning_platform.back_stage_manage.service.MajorService;
import com.houyu.online_learning_platform.back_stage_manage.vo.MajorVO;
import com.houyu.online_learning_platform.utils.responseMessage.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/major")
public class MajorController {
    @Autowired
    private MajorService majorService;

    //获取专业列表
    @GetMapping("/getmajorpage")
    public ResponseMessage getMajorList(@RequestParam String majorName, Pageable pageable){
        return ResponseMessage.ok(majorService.getMajorList(majorName,pageable));
    }
    //新建、更新专业
    @PostMapping("/savemajor")
    public ResponseMessage saveMajor(@RequestBody MajorVO majorVO){
        return ResponseMessage.ok(majorService.saveMajor(majorVO));
    }
    //删除专业
    @GetMapping("deletemajor")
    public  ResponseMessage deleteMajor(@RequestParam Integer id){
        return ResponseMessage.ok(majorService.deleteMajor(id));
    }
}
