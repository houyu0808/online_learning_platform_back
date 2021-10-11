package com.houyu.online_learning_platform.loginAndRegister.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Table(name="user_information")
@Entity
public class Admin {
    @Id
    private Integer id;
    private String username;
    private String password;
}
