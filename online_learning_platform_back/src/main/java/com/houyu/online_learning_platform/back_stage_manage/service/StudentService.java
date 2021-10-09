package com.houyu.online_learning_platform.back_stage_manage.service;

import com.houyu.online_learning_platform.back_stage_manage.entity.Student;
import com.houyu.online_learning_platform.back_stage_manage.vo.StudentVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface StudentService {
    //获取学生列表
    Page<Student> getStudentList(String username, Pageable pageable);
    //新建学生信息
    String saveStudent(StudentVO studentVO);
    //删除学生信息
    String deleteStudent(Integer id);
}
