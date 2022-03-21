package com.houyu.online_learning_platform.functions.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;

@Getter
@Setter
@Table(name = "comment_list2")
@Entity
public class Comment2 {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Integer id;
    private String commentText;
    private Integer fabulous;
    private Integer step;
    private Date createdTime;
    private Integer belongCommentId;
    private String commentUserCode;
    private String commentUsername;
    private String commentHeadImg;
}
