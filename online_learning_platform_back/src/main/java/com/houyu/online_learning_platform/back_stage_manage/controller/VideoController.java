package com.houyu.online_learning_platform.back_stage_manage.controller;

import com.houyu.online_learning_platform.back_stage_manage.service.VideoService;
import com.houyu.online_learning_platform.back_stage_manage.vo.VideoVO;
import com.houyu.online_learning_platform.utils.responseMessage.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/video")
public class VideoController {
    @Autowired
    private VideoService videoService;

    @PostMapping("/savevideo")
    public ResponseMessage saveVideoPage(@RequestParam("file1")MultipartFile file1,@RequestParam("file2")MultipartFile file2, VideoVO videoVO){
        return ResponseMessage.ok(videoService.saveVideo(file1,file2,videoVO));
    }

    @GetMapping("/getvideopage")
    public ResponseMessage getVideoPage(@RequestParam String name, Pageable pageable){
        return ResponseMessage.ok(videoService.getVideoPage(name,pageable));
    }

    @GetMapping("deletevideo")
    public ResponseMessage deleteVideo(@RequestParam Integer id){
        return ResponseMessage.ok(videoService.deleteVideo(id));
    }
}
