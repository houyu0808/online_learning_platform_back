package com.houyu.online_learning_platform.back_stage_manage.vo;

import lombok.Data;

import java.math.BigInteger;
import java.util.Date;

@Data
public class Comment2VO {
    private Integer id;
    private String commentText;
    private Integer fabulous;
    private Integer step;
    private Date createdTime;
    private Integer belongCommentId;
    private String commentUserCode;
    private String commentUsername;
}
