package com.houyu.online_learning_platform.functions.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;

@Getter
@Setter
@Table(name = "task_list")
@Entity
public class Task {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Integer id;
    private String name;
    private String fileUrl;
    private BigInteger belongClassCode;
    private String publisherCode;
    private String startTime;
    private String endTime;
    private String comment;

}
