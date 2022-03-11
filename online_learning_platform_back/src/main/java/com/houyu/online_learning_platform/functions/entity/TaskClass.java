package com.houyu.online_learning_platform.functions.entity;


import lombok.Getter;
import lombok.Setter;
import org.omg.CORBA.PRIVATE_MEMBER;

import javax.persistence.*;

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
    private String belongClassCode;
    private String file_url;
    private String upload_time;
    private Integer status;
}
