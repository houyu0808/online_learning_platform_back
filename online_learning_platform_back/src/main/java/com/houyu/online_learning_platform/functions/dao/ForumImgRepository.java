package com.houyu.online_learning_platform.functions.dao;

import com.houyu.online_learning_platform.functions.entity.ForumImg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ForumImgRepository extends JpaRepository<ForumImg,Integer> {
    List<ForumImg> findAllByBelongForumId(Integer id);
}
