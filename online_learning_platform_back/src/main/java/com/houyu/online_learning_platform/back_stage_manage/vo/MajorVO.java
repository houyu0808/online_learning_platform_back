package com.houyu.online_learning_platform.back_stage_manage.vo;

import lombok.Data;

@Data
public class MajorVO {
    private Integer id;
    //专业名
    private String majorName;
    //专业编码
    private Integer majorCode;
    //所属学院
    private Integer affiliatedCollegeCode;
}
