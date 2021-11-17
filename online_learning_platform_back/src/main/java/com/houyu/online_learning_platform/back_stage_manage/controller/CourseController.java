package com.houyu.online_learning_platform.back_stage_manage.controller;

import com.houyu.online_learning_platform.back_stage_manage.service.CourseService;
import com.houyu.online_learning_platform.back_stage_manage.vo.CourseVO;
import com.houyu.online_learning_platform.utils.responseMessage.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;

@RestController
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @GetMapping("/getcoursepage")
    public ResponseMessage getCoursePage(@RequestParam String courseName, Pageable pageable){
        return ResponseMessage.ok(courseService.getCourseList(courseName,pageable));
    }

    @PostMapping("/savecourse")
    public ResponseMessage saveCourse(@RequestBody CourseVO courseVO){
        return ResponseMessage.ok(courseService.saveCourse(courseVO));
    }

    @GetMapping("deletecourse")
    public ResponseMessage deleteCourse(@RequestParam Integer id){
        return ResponseMessage.ok(courseService.deleteCourse(id));
    }

}
