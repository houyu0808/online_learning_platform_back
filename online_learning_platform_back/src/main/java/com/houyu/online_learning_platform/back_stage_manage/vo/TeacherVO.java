package com.houyu.online_learning_platform.back_stage_manage.vo;

import lombok.Data;

import java.math.BigInteger;

@Data
public class TeacherVO {
    private Integer id;
    //身份
    private String identify;
    //用户名
    private String username;
    private String password;
    private String phoneNumber;
    //性别
    private String sex;
    //工号
    private String employeeNumber;
    //所属学院
    private BigInteger affiliatedCollegeCode;
    private Integer clickTimes;
    private String autograph;
    private String headImgUrl;
}
