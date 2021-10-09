package com.houyu.online_learning_platform.back_stage_manage.dao;

import com.houyu.online_learning_platform.back_stage_manage.entity.College;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface CollegeRepository extends JpaRepository<College, Integer> {
    Page<College> findByCollegeNameContaining(String collegeName, Pageable pageable);
    College findByCollegeName(String collegeName);
    College findByCollegeCode(BigInteger collegeCode);
}
