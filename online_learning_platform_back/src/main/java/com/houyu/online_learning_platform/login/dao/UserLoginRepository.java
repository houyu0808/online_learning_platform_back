package com.houyu.online_learning_platform.login.dao;

import com.houyu.online_learning_platform.login.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserLoginRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
