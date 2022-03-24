package com.houyu.online_learning_platform.functions.controller;

import com.houyu.online_learning_platform.functions.service.ForumService;
import com.houyu.online_learning_platform.utils.responseMessage.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/forum")
public class ForumController {
    @Autowired
    private ForumService forumService;

    @PostMapping("/publishforum")
    public ResponseMessage publishForum(@RequestParam(required = false)MultipartFile[] files,String userNumber,String forumContent,String publishTime,String identify){
        forumService.publishForum(files,userNumber,forumContent,publishTime,identify);
        return ResponseMessage.ok(200,"发布成功!");
    }
}
