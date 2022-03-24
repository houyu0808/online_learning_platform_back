package com.houyu.online_learning_platform.functions.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Table(name = "forum_img")
@Entity
public class ForumImg {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Integer id;
    private Integer belongForumId;
    private String imgUrl;
}
