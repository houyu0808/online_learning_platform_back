package com.houyu.online_learning_platform.back_stage_manage.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigInteger;

@Getter
@Setter
@Table(name = "student_info_list")
@Entity
public class Student {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Integer id;
    //身份
    private String identify;
    //用户名
    private String username;
    //性别
    private String sex;
    //学号
    private String stuNumber;
    //密码
    private String password;
    //电话号码
    private String phoneNumber;
    //所属班级编码
    private BigInteger affiliatedClassCode;
    //所属班级名称
    private String affiliatedClassName;
    //所属专业编码
    private BigInteger affiliatedMajorCode;
    //所属专业名称
    private String affiliatedMajorName;
    //所属学院编码
    private BigInteger affiliatedCollegeCode;
    //所属学院名称
    private String affiliatedCollegeName;
    private String headImgUrl;
}
