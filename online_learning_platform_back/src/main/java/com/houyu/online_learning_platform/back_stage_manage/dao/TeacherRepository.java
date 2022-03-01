package com.houyu.online_learning_platform.back_stage_manage.dao;

import com.houyu.online_learning_platform.back_stage_manage.entity.Teacher;
import com.houyu.online_learning_platform.back_stage_manage.entity.Video;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher,Integer> {
    Page<Teacher> findByUsernameContaining(String username, Pageable pageable);
    Teacher findByUsername(String username);
    Teacher findByEmployeeNumber(String employeeNumber);
    void deleteAllByAffiliatedCollegeCode(BigInteger code);

    @Query(nativeQuery = true,value = "select * from teacher_info_list order by click_times DESC")
    List<Teacher> findAllOrderByClickTimes();
}
