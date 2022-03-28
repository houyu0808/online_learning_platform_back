package com.houyu.online_learning_platform.functions.dao;

import com.houyu.online_learning_platform.functions.entity.Forum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ForumRepository extends JpaRepository<Forum,Integer> {
    Forum findByVerifyString(String verifyString);

    @Query(nativeQuery = true,value = "select * from forum order by ?#{#pageable}")
    Page<Forum> findAllOrderBy(Pageable pageable);
}
