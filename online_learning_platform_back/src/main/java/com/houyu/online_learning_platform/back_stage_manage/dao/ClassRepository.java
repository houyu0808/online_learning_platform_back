package com.houyu.online_learning_platform.back_stage_manage.dao;

import com.houyu.online_learning_platform.back_stage_manage.entity.Class;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface ClassRepository extends JpaRepository<Class, Integer> {
    Page<Class> findByClassNameContaining(String className, Pageable pageable);
    Class findByClassName(String className);
    Class findByClassCode(BigInteger classCode);
}
