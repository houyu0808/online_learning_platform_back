package com.houyu.online_learning_platform.loginAndRegister.vo;

import lombok.Data;

@Data
public class UserVO {
    private Integer id;
    private String username;
    private String password;
    private String institute;
    private String classAndGrades;
}
