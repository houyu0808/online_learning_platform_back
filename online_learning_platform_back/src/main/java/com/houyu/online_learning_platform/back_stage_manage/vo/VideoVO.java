package com.houyu.online_learning_platform.back_stage_manage.vo;

import lombok.Data;

import java.math.BigInteger;
import java.util.Date;

@Data
public class VideoVO {
    private Integer id;
    //名称
    private String name;
    //视频编码
    private Integer videoCode;
    //封面访问路径
    private String imageUrl;
    //视频访问路径
    private String videoUrl;
    //所属课程id
    private BigInteger belongCourseCode;
    //所属课程名
    private String belongCourseName;
    //所属教师id
    private String belongTeacherCode;
    //所属教师名
    private String belongTeacherName;
    private Date createdTime;
}
