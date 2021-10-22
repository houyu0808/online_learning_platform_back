package com.houyu.online_learning_platform.back_stage_manage.dao;

import com.houyu.online_learning_platform.back_stage_manage.entity.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher,Integer> {
    Page<Teacher> findByUsernameContaining(String username, Pageable pageable);
    Teacher findByUsername(String username);
    Teacher findByEmployeeNumber(String employeeNumber);
    void deleteAllByAffiliatedCollegeCode(BigInteger code);

}
