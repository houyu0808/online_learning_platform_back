package com.houyu.online_learning_platform.functions.dao;

import com.houyu.online_learning_platform.functions.entity.ForumLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ForumLikeRepository extends JpaRepository<ForumLike,Integer> {
    ForumLike findByBelongForumIdAndLikeUserNumber(Integer id,String userNumber);
}
