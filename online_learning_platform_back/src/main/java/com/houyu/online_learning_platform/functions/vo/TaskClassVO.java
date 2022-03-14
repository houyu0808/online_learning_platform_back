package com.houyu.online_learning_platform.functions.vo;

import lombok.Data;

import java.math.BigInteger;

@Data
public class TaskClassVO {
    private Integer id;
    private Integer belongTaskId;
    private String stuNumber;
    private String stuName;
    private BigInteger belongClassCode;
    private String belongClassName;
    private String fileUrl;
    private String uploadTime;
    private String status;
}
