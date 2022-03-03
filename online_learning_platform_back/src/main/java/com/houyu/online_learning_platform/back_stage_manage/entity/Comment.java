package com.houyu.online_learning_platform.back_stage_manage.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;

@Getter
@Setter
@Table(name = "comment_list")
@Entity
public class Comment {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Integer id;
    private String commentText;
    private Integer belongVideoId;
    private BigInteger like;
    private BigInteger unlike;
    private Date createdTime;
    private Integer belongCommentId;
    private String commentUserCode;
}
