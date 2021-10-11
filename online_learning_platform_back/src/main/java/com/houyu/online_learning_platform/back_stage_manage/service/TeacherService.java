package com.houyu.online_learning_platform.back_stage_manage.service;

import com.houyu.online_learning_platform.back_stage_manage.entity.Teacher;
import com.houyu.online_learning_platform.back_stage_manage.vo.TeacherVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface TeacherService {
    Page<Teacher> getTeacherList(String username, Pageable pageable);
    String saveTeacher(TeacherVO teacherVO);
    String deleteTeacher(Integer id);
}
