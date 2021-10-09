package com.houyu.online_learning_platform.back_stage_manage.vo;

import lombok.Data;

import java.math.BigInteger;

@Data
public class ClassVO {
    private Integer id;
    private String className;
    private BigInteger classCode;
    private BigInteger affiliatedMajorCode;
    private BigInteger affiliatedCollegeCode;
}
