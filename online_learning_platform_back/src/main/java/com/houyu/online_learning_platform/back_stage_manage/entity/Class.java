package com.houyu.online_learning_platform.back_stage_manage.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigInteger;

@Getter
@Setter
@Table(name = "class_info_list")
@Entity
public class Class {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Integer id;
    private String className;
    private BigInteger classCode;
    private BigInteger affiliatedMajorCode;
    private String affiliatedMajorName;
    private BigInteger affiliatedCollegeCode;
    private String affiliatedCollegeName;
}
