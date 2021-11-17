package com.houyu.online_learning_platform.back_stage_manage.dao;

import com.houyu.online_learning_platform.back_stage_manage.entity.Video;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoRepository extends JpaRepository<Video,Integer> {
     Page<Video> findByNameContaining(String name, Pageable pageable);
     void deleteById(Integer id);
     Video findByVideoCode(Integer videoCode);
}
