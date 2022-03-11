package com.houyu.online_learning_platform.functions.vo;

import lombok.Data;

@Data
public class TaskClassVO {
    private Integer id;
    private Integer belongTaskId;
    private String stuNumber;
    private String stuName;
    private String belongClassCode;
    private String file_url;
    private String upload_time;
    private Integer status;
}
