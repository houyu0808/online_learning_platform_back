package com.houyu.online_learning_platform.back_stage_manage.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;

@Getter
@Setter
@Table(name = "video_info")
@Entity
public class Video {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
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
    private Integer clickTimes;
    private String videoIntroduce;
}
