package com.houyu.online_learning_platform.functions.vo;

import lombok.Data;

@Data
public class ForumLikeVO {
    private Integer id;
    private Integer belongForumId;
    private String likeUserNumber;
}
