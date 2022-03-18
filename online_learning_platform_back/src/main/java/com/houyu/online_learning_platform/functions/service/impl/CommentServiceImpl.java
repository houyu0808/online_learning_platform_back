package com.houyu.online_learning_platform.functions.service.impl;

import com.houyu.online_learning_platform.back_stage_manage.dao.VideoRepository;
import com.houyu.online_learning_platform.back_stage_manage.entity.Video;
import com.houyu.online_learning_platform.functions.dao.Comment2Repository;
import com.houyu.online_learning_platform.functions.dao.CommentRepository;
import com.houyu.online_learning_platform.functions.entity.Comment;
import com.houyu.online_learning_platform.functions.entity.Comment2;
import com.houyu.online_learning_platform.functions.service.CommentService;
import com.houyu.online_learning_platform.functions.vo.Comment2VO;
import com.houyu.online_learning_platform.functions.vo.CommentVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private Comment2Repository comment2Repository;
    @Autowired
    private VideoRepository videoRepository;

    @Override
    public void addComments(CommentVO commentVO) {
        Comment comment = new Comment();
        BeanUtils.copyProperties(commentVO,comment);
        comment.setFabulous(0);
        comment.setStep(0);
        comment.setCreatedTime(new Date());
        commentRepository.save(comment);
    }

    @Override
    public void addChildComments(Comment2VO comment2VO) {
        Comment2 comment2 = new Comment2();
        BeanUtils.copyProperties(comment2VO,comment2);
        comment2.setFabulous(0);
        comment2.setStep(0);
        comment2.setCreatedTime(new Date());
        comment2Repository.save(comment2);
    }

    @Override
    public List<CommentVO> getVideoComments(Integer id) {
        List<Comment> commentList = commentRepository.findAllByBelongVideoId(id);
        List<CommentVO> commentVOList = new ArrayList<>();
        for(Comment comment: commentList){
            CommentVO commentVO = new CommentVO();
            BeanUtils.copyProperties(comment,commentVO);
            List<Comment2> comment2List = comment2Repository.findAllByBelongCommentId(comment.getId());
            List<Comment2VO> comment2VOList = new ArrayList<>();
            for(Comment2 comment2: comment2List){
                Comment2VO comment2VO = new Comment2VO();
                BeanUtils.copyProperties(comment2,comment2VO);
                comment2VOList.add(comment2VO);
            }
            commentVO.setChildCommentList(comment2VOList);
            commentVOList.add(commentVO);
        }
        return commentVOList;
    }
}
