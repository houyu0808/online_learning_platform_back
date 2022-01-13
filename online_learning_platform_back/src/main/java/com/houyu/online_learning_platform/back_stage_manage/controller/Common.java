package com.houyu.online_learning_platform.back_stage_manage.controller;

import com.houyu.online_learning_platform.back_stage_manage.service.CommonService;
import com.houyu.online_learning_platform.utils.responseMessage.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/common")
public class Common {
    @Autowired
    private CommonService commonService;

    @GetMapping("/addclicktimes")
    public ResponseMessage addClickTimes(@RequestParam Integer id){
        commonService.addClickTimes(id);
        return ResponseMessage.ok();
    }
    @GetMapping("/getcarousel")
    public ResponseMessage getCarousel(){
        return ResponseMessage.ok(commonService.getCarousel());
    }
    @GetMapping("/getextensionlist")
    public  ResponseMessage getExtensionList(){
        return  ResponseMessage.ok(commonService.getExtensionList());
    }
}
