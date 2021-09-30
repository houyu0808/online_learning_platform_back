package com.houyu.online_learning_platform.back_stage_manage.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Table(name = "college_info_list")
@Entity
public class College {
    @Id
    private Integer id;
    //学院名
    private String collegeName;
    //学院代码
    private Integer collegeCode;
}
