package com.houyu.online_learning_platform.functions.service;

import com.houyu.online_learning_platform.functions.vo.TaskVO;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface TaskService {
    void addTask(MultipartFile file, TaskVO taskVO);
    void deleteTask(Integer id);
}
