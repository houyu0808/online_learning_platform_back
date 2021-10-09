package com.houyu.online_learning_platform.back_stage_manage.vo;

import lombok.Data;

import java.math.BigInteger;

@Data
public class CollegeVO {
    private Integer id;
    //学院名
    private String collegeName;
    //学院代码
    private BigInteger collegeCode;
}
