package com.houyu.online_learning_platform.back_stage_manage.dao;

import com.houyu.online_learning_platform.back_stage_manage.entity.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course,Integer> {
    Page<Course> findByCourseNameContaining(String courseName, Pageable pageable);
    Course findByCourseCode(BigInteger courseCode);
    void deleteAllByAffiliatedCollegeCode(BigInteger code);
    void deleteAllByAffiliatedMajorCode(BigInteger code);
    List<Course> findAllByAffiliatedMajorCode(BigInteger majorCode);
    List<Course> findAllByAffiliatedCollegeCode(BigInteger collegeCode);
}
