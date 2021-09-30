package com.houyu.online_learning_platform.back_stage_manage.dao;

import com.houyu.online_learning_platform.back_stage_manage.entity.College;
import com.houyu.online_learning_platform.back_stage_manage.vo.CollegeVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CollegeRepository extends JpaRepository<College, CollegeVO> {
}
