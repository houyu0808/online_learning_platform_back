package com.houyu.online_learning_platform.back_stage_manage.vo;

import lombok.Data;

import java.math.BigInteger;

@Data
public class CourseVO {
    private Integer id;
    private String courseName;
    private BigInteger courseCode;
    private BigInteger affiliatedMajorCode;
    private BigInteger affiliatedCollegeCode;;
    private String teacher;
    private Integer status;
    private Integer clickTimes;
}
