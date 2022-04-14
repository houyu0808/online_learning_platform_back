package com.houyu.online_learning_platform.functions.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Table(name = "broadcast_list")
@Entity
public class Broadcast {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Integer id;
    private String broadcastTitle;
    private String broadcastNumber;
    private String liveUrl;
    private String obsPushUrl;
    private String obsKey;
    private Integer status;
}
