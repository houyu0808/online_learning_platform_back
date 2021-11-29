package com.houyu.online_learning_platform.back_stage_manage.controller;

import com.houyu.online_learning_platform.back_stage_manage.entity.Major;
import com.houyu.online_learning_platform.back_stage_manage.service.MajorService;
import com.houyu.online_learning_platform.back_stage_manage.vo.MajorVO;
import com.houyu.online_learning_platform.utils.responseMessage.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        String result = majorService.saveMajor(majorVO);
        if(result.equals("更新成功") || result.equals("创建成功")){
            return ResponseMessage.ok(result);
        }else{
            return ResponseMessage.error(500,result);
        }
    }
    //删除专业
    @GetMapping("deletemajor")
    public  ResponseMessage deleteMajor(@RequestParam Integer[] ids){
        return ResponseMessage.ok(majorService.deleteMajor(ids));
    }

    @GetMapping("getmajorbyid")
    public  ResponseMessage getMajorById(@RequestParam Integer id){
        return ResponseMessage.ok(majorService.getMajorById(id));
    }
}
