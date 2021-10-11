package com.houyu.online_learning_platform.loginAndRegister.dao;

import com.houyu.online_learning_platform.back_stage_manage.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeachersRepository extends JpaRepository<Teacher,Integer> {
    Teacher findByPhoneNumber(String phoneNumber);
    Teacher findByEmployeeNumber(String employeeNumber);
}
