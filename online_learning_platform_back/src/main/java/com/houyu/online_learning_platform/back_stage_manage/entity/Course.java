package com.houyu.online_learning_platform.back_stage_manage.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigInteger;


@Getter
@Setter
@Table(name = "course_info_list")
@Entity
public class Course {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Integer id;
    private String courseName;
    private BigInteger courseCode;
    //所属专业编码
    private BigInteger affiliatedMajorCode;
    //所属专业名称
    private String affiliatedMajorName;
    //所属学院编码
    private BigInteger affiliatedCollegeCode;
    //所属学院名称
    private String affiliatedCollegeName;
    private String teacher;
    private Integer status;
}
