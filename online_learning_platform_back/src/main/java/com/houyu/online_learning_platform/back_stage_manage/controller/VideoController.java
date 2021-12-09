package com.houyu.online_learning_platform.back_stage_manage.controller;

import com.houyu.online_learning_platform.back_stage_manage.service.VideoService;
import com.houyu.online_learning_platform.back_stage_manage.vo.VideoVO;
import com.houyu.online_learning_platform.utils.responseMessage.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/video")
public class VideoController {
    @Autowired
    private VideoService videoService;

    @PostMapping("/savevideo")
    public ResponseMessage saveVideoPage(@RequestParam("file1")MultipartFile file1,@RequestParam("file2")MultipartFile file2,VideoVO videoVO){
        String result = videoService.saveVideo(file1,file2,videoVO);
        if(result.equals("更新成功") || result.equals("创建成功")){
            return ResponseMessage.ok(result);
        }else{
            return ResponseMessage.error(500,result);
        }
    }

    @GetMapping("/getvideopage")
    public ResponseMessage getVideoPage(@RequestParam String name, Pageable pageable){
        return ResponseMessage.ok(videoService.getVideoPage(name,pageable));
    }

    @GetMapping("/deletevideo")
    public ResponseMessage deleteVideo(@RequestParam Integer[] ids){
        return ResponseMessage.ok(videoService.deleteVideo(ids));
    }
    @GetMapping("/getvideobyid")
    public ResponseMessage getVideoById(@RequestParam Integer id){
        return ResponseMessage.ok(videoService.getVideoById(id));
    }
}
