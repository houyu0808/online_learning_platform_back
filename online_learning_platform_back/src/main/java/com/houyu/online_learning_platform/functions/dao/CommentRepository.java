package com.houyu.online_learning_platform.functions.dao;

import com.houyu.online_learning_platform.functions.entity.Comment;
import com.houyu.online_learning_platform.functions.vo.CommentVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, CommentVO> {
   List<Comment> findAllByBelongVideoId(Integer id);
}
