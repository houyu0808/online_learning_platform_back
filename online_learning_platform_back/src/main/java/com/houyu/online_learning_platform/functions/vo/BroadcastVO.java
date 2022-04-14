package com.houyu.online_learning_platform.functions.vo;

import lombok.Data;

@Data
public class BroadcastVO {
    private Integer id;
    private String broadcastTitle;
    private String broadcastNumber;
    private String liveUrl;
    private String obsPushUrl;
    private String obsKey;
    private Integer status;
    private String headImgUrl;
    private String username;
}
