package com.houyu.online_learning_platform.functions.controller;

import com.houyu.online_learning_platform.functions.service.ForumService;
import com.houyu.online_learning_platform.functions.vo.ForumCommentVO;
import com.houyu.online_learning_platform.utils.responseMessage.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.data.domain.Pageable;

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
    @GetMapping("/getforumlist")
    public ResponseMessage getForumList(Pageable pageable){
        return ResponseMessage.ok(forumService.getForumList(pageable));
    }
    @PostMapping("/addlike")
    public ResponseMessage addLike(@RequestParam Integer forumId,@RequestParam String userNumber){
        forumService.addLike(forumId,userNumber);
        return ResponseMessage.ok(200,"success");
    }
    @PostMapping("/cancellike")
    public ResponseMessage cancelLike(@RequestParam Integer forumId,@RequestParam String userNumber){
        forumService.cancelLike(forumId,userNumber);
        return ResponseMessage.ok(200,"success");
    }

    @PostMapping("/addcomment")
    public ResponseMessage addComment(@RequestBody ForumCommentVO forumCommentV0){
        forumService.addComments(forumCommentV0);
        return ResponseMessage.ok(200,"评论成功");
    }
}
