package com.houyu.online_learning_platform.functions.entity;


import lombok.Getter;
import lombok.Setter;
import org.omg.CORBA.PRIVATE_MEMBER;

import javax.persistence.*;
import java.math.BigInteger;

@Getter
@Setter
@Table(name = "task_class_list")
@Entity
public class TaskClass {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
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
