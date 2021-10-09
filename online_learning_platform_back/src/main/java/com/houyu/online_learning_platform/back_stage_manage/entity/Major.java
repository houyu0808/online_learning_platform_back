package com.houyu.online_learning_platform.back_stage_manage.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigInteger;

@Getter
@Setter
@Table(name = "major_info_list")
@Entity
public class Major {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Integer id;
    //专业名
    private String majorName;
    //专业编码
    private BigInteger majorCode;
    //所属学院编码
    private BigInteger affiliatedCollegeCode;
    //所属学院名称
    private String affiliatedCollegeName;
}
