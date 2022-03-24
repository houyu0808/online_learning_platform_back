package com.houyu.online_learning_platform.functions.vo;

import lombok.Data;

@Data
public class ForumVO {
    private Integer id;
    private String forumContent;
    private String publishTime;
    private String publisherNumber;
    private Integer likes;
    private Integer viewTimes;
    private Integer imgJudge;
}
