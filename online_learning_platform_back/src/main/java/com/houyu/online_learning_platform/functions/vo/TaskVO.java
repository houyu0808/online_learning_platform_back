package com.houyu.online_learning_platform.functions.vo;

import lombok.Data;

import java.math.BigInteger;
import java.util.Date;

@Data
public class TaskVO {
    private Integer id;
    private String name;
    private String fileUrl;
    private BigInteger belongClassCode;
    private String publisherCode;
    private String startTime;
    private String endTime;
    private String comment;
}
