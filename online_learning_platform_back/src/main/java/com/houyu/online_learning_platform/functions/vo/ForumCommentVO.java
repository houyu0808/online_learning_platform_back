package com.houyu.online_learning_platform.functions.vo;

import lombok.Data;

@Data
public class ForumCommentVO {
    private Integer id;
    private Integer belongForumId;
    private String comment;
    private String commentUserNumber;
    private String identify;
    private String username;
    private String headImgUrl;
}
