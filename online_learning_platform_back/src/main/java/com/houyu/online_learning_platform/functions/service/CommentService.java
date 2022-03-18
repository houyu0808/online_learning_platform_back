package com.houyu.online_learning_platform.functions.service;

import com.houyu.online_learning_platform.back_stage_manage.entity.Video;
import com.houyu.online_learning_platform.functions.vo.Comment2VO;
import com.houyu.online_learning_platform.functions.vo.CommentVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommentService {
    void addComments(CommentVO commentVO);
    void addChildComments(Comment2VO comment2VO);
    List<CommentVO> getVideoComments(Integer id);
}
