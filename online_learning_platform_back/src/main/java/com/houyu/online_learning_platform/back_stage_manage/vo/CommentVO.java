package com.houyu.online_learning_platform.back_stage_manage.vo;

import lombok.Data;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@Data
public class CommentVO {
    private Integer id;
    private String commentText;
    private Integer belongVideoId;
    private Integer fabulous;
    private Integer step;
    private Date createdTime;
    private String commentUserCode;
    private String commentUsername;
    private List<Comment2VO> childCommentList;
}