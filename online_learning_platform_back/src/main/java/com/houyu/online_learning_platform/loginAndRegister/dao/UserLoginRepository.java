package com.houyu.online_learning_platform.loginAndRegister.dao;

import com.houyu.online_learning_platform.loginAndRegister.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserLoginRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
