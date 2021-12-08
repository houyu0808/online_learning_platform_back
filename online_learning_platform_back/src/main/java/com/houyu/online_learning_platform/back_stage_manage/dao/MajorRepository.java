package com.houyu.online_learning_platform.back_stage_manage.dao;

import com.houyu.online_learning_platform.back_stage_manage.entity.College;
import com.houyu.online_learning_platform.back_stage_manage.entity.Major;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface MajorRepository extends JpaRepository<Major, Integer> {
    Page<Major> findByMajorNameContaining(String majorName, Pageable pageable);
    Major findByMajorName(String collegeName);
    Major findByMajorCode(BigInteger collegeCode);
    void deleteAllByAffiliatedCollegeCode(BigInteger code);
    List<Major> findAllByAffiliatedCollegeCode(BigInteger collegeCode);
}
