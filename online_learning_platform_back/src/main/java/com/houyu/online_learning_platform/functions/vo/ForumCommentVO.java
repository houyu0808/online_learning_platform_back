package com.houyu.online_learning_platform.functions.vo;

import lombok.Data;

@Data
public class ForumCommentVO {
    private Integer id;
    private Integer belongForumId;
    private String comment;
    private String commentUserNumber;
}
