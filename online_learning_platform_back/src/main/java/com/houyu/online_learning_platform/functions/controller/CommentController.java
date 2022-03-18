package com.houyu.online_learning_platform.functions.controller;


import com.houyu.online_learning_platform.functions.service.CommentService;
import com.houyu.online_learning_platform.functions.vo.Comment2VO;
import com.houyu.online_learning_platform.functions.vo.CommentVO;
import com.houyu.online_learning_platform.utils.responseMessage.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping("/addcomments")
    public ResponseMessage addComments(@RequestBody CommentVO commentVO){
        commentService.addComments(commentVO);
        return ResponseMessage.ok(200,"评论成功");
    }
    @PostMapping("/addchildcomments")
    public ResponseMessage addChildComments(@RequestBody Comment2VO comment2VO){
        commentService.addChildComments(comment2VO);
        return ResponseMessage.ok(200,"评论成功");
    }

    @GetMapping("/getvideocomments")
    public ResponseMessage getVideoComments(@RequestParam Integer id){
        return ResponseMessage.ok(commentService.getVideoComments(id));
    }
}
