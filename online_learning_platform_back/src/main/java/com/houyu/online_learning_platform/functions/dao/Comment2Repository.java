package com.houyu.online_learning_platform.functions.dao;

import com.houyu.online_learning_platform.functions.entity.Comment2;
import com.houyu.online_learning_platform.functions.vo.Comment2VO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Comment2Repository extends JpaRepository<Comment2, Comment2VO> {
    List<Comment2> findAllByBelongCommentId(Integer id);
}
