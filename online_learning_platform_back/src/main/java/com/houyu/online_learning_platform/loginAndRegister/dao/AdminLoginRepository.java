package com.houyu.online_learning_platform.loginAndRegister.dao;

import com.houyu.online_learning_platform.loginAndRegister.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminLoginRepository extends JpaRepository<Admin, Long> {
    Admin findByUsername(String username);
}
