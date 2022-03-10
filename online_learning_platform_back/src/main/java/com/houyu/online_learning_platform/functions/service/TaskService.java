package com.houyu.online_learning_platform.functions.service;

import com.houyu.online_learning_platform.functions.entity.Task;
import com.houyu.online_learning_platform.functions.vo.TaskVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
public interface TaskService {
    void addTask(MultipartFile file, TaskVO taskVO);
    void deleteTask(Integer id);
    Page<Task> getTeacherTaskPage(String teacherCode, Pageable pageable);
}
