package com.houyu.online_learning_platform.back_stage_manage.vo;

import lombok.Data;

import java.math.BigInteger;

@Data
public class StudentVO {
    private Integer id;
    //身份
    private String identify;
    //用户名
    private String username;
    //性别
    private String sex;
    //学号
    private String stuNumber;
    private String password;
    private String phoneNumber;
    //所属班级
    private BigInteger affiliatedClassCode;
    //所属专业
    private BigInteger affiliatedMajorCode;
    //所属学院
    private BigInteger affiliatedCollegeCode;
    private String headImgUrl;
}
