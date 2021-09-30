package com.houyu.online_learning_platform.back_stage_manage.vo;

import lombok.Data;

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
    private Integer stuNumber;
    //所属学院
    private Integer affiliatedCollegeCode;
}
