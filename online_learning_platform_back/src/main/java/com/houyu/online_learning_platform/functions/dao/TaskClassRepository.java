package com.houyu.online_learning_platform.functions.dao;

import com.houyu.online_learning_platform.functions.entity.TaskClass;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskClassRepository extends JpaRepository<TaskClass,Integer> {
    List<TaskClass> findAllByBelongTaskId(Integer id);
    Page<TaskClass> findByBelongTaskId(Integer id, Pageable pageable);
    TaskClass findByBelongTaskIdAndStuNumber(Integer taskId,String stuNumber);
    List<TaskClass> findAllByStuNumber(String stuNumber);
}
