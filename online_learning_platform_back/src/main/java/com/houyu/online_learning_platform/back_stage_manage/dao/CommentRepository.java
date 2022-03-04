package com.houyu.online_learning_platform.back_stage_manage.dao;

import com.houyu.online_learning_platform.back_stage_manage.entity.Comment;
import com.houyu.online_learning_platform.back_stage_manage.vo.CommentVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, CommentVO> {
   List<Comment> findAllByBelongVideoId(Integer id);
}
