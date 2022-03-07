package com.houyu.online_learning_platform.functions.dao;

import com.houyu.online_learning_platform.functions.entity.Task;
import com.houyu.online_learning_platform.functions.vo.TaskVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
}
