package com.houyu.online_learning_platform.loginAndRegister.dao;

import com.houyu.online_learning_platform.back_stage_manage.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface StudentsRepository extends JpaRepository<Student,Integer> {
    Student findByPhoneNumber(String phoneNumber);
    Student findByStuNumber(String stuNumber);
}
