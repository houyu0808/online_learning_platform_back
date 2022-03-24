package com.houyu.online_learning_platform.functions.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Table(name = "forum")
@Entity
public class Forum {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Integer id;
    private String forumContent;
    private String publishTime;
    private String publisherNumber;
    private Integer likes;
    private Integer viewTimes;
    private Integer imgJudge;
    private String identify;
}
