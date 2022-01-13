package com.houyu.online_learning_platform.back_stage_manage.dao;

import com.houyu.online_learning_platform.back_stage_manage.entity.Video;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VideoRepository extends JpaRepository<Video,Integer> {
     Page<Video> findByNameContaining(String name, Pageable pageable);
     void deleteById(Integer id);

     Video findByVideoCode(Integer videoCode);

     @Query(nativeQuery=true,value = "select * from video_info order by created_time DESC limit 8")
     List<Video> getAllByOrderByCreatedTimeDesc();

     Optional<Video> findById(Integer id);

     @Query(nativeQuery = true,value = "select * from video_info order by click_times DESC limit 6")
     List<Video> getAllByClickTimes();

}
