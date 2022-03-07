package com.houyu.online_learning_platform.back_stage_manage.controller;

import com.houyu.online_learning_platform.back_stage_manage.service.CommonService;
import com.houyu.online_learning_platform.utils.responseMessage.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

@RestController
@RequestMapping("/common")
public class CommonController {
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
    public ResponseMessage getExtensionList(){
        return  ResponseMessage.ok(commonService.getExtensionList());
    }

    @GetMapping("/getstudentinformation")
    public ResponseMessage getStudentInformation(@RequestParam String username){
        return ResponseMessage.ok(commonService.getStudentInformation(username));
    }

    @GetMapping("/getteacherinformation")
    public ResponseMessage getTeacherInformation(@RequestParam String username){
        return ResponseMessage.ok(commonService.getTeacherInformation(username));
    }
    @GetMapping("/getstudenthotlist")
    public ResponseMessage getStudentHotList(@RequestParam BigInteger majorCode){
        return ResponseMessage.ok(commonService.getStudentHotList(majorCode));
    }
    @GetMapping("/getteacherhotlist")
    public ResponseMessage getTeacherHotList(@RequestParam BigInteger collegeCode){
        return ResponseMessage.ok(commonService.getTeacherHotList(collegeCode));
    }
    @GetMapping("/getrecommendteacher")
    public ResponseMessage getRecommendTeacher(){
        return ResponseMessage.ok(commonService.getRecommendTeacher());
    }

    @GetMapping("/getvideoinfo")
    public ResponseMessage getVideoInfo(@RequestParam Integer id){
        return ResponseMessage.ok(commonService.getVideoInfo(id));
    }

    @GetMapping("/getvideolist")
    public  ResponseMessage getVideoList(@RequestParam String teacherCode){
        return ResponseMessage.ok(commonService.getVideoList(teacherCode));
    }

    @GetMapping("/getteacherinfo")
    public ResponseMessage getTeacherInfo(@RequestParam String teacherCode){
        return ResponseMessage.ok(commonService.getTeacherInfo(teacherCode));
    }
    @GetMapping("/gettodayrecommend")
    public ResponseMessage getTodayRecommend(){
        return ResponseMessage.ok(commonService.getTodayRecommend());
    }
}
