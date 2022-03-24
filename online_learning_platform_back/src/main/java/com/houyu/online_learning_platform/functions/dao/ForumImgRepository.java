package com.houyu.online_learning_platform.functions.dao;

import com.houyu.online_learning_platform.functions.entity.ForumImg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ForumImgRepository extends JpaRepository<ForumImg,Integer> {
}
