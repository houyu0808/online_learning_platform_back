package com.houyu.online_learning_platform.back_stage_manage.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Table(name = "teacher_info_list")
@Entity
public class Teacher {
    @Id
    private Integer id;
    //身份
    private String identify;
    //用户名
    private String username;
    //性别
    private String sex;
    //工号
    private Integer employeeNumber;
    //所属学院
    private Integer affiliatedCollegeCode;
}
