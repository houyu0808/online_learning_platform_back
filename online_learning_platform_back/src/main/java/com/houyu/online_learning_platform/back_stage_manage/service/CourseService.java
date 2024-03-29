package com.houyu.online_learning_platform.back_stage_manage.service;

import com.houyu.online_learning_platform.back_stage_manage.entity.Course;
import com.houyu.online_learning_platform.back_stage_manage.entity.Major;
import com.houyu.online_learning_platform.back_stage_manage.vo.CourseVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
public interface CourseService {
    Page<Course> getCourseList(String courseName, Pageable pageable);
    String saveCourse(CourseVO courseVO);
    String deleteCourse(Integer[] ids);
    CourseVO getCourseById(Integer id);
    List<Course> getCourseByMajor(BigInteger majorCode);
}
