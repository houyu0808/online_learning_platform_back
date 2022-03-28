package com.houyu.online_learning_platform.functions.dao;

import com.houyu.online_learning_platform.functions.entity.ForumComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ForumCommentRepository extends JpaRepository<ForumComment,Integer> {
    List<ForumComment> findAllByBelongForumId(Integer id);
}
