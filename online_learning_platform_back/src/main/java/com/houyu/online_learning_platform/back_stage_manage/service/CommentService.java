package com.houyu.online_learning_platform.back_stage_manage.service;

import com.houyu.online_learning_platform.back_stage_manage.vo.Comment2VO;
import com.houyu.online_learning_platform.back_stage_manage.vo.CommentVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommentService {
    void addComments(CommentVO commentVO);
    void addChildComments(Comment2VO comment2VO);
    List<CommentVO> getVideoComments(Integer id);
}
