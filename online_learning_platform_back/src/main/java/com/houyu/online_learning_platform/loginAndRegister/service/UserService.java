package com.houyu.online_learning_platform.loginAndRegister.service;

import com.houyu.online_learning_platform.back_stage_manage.vo.StudentVO;
import com.houyu.online_learning_platform.back_stage_manage.vo.TeacherVO;
import com.houyu.online_learning_platform.loginAndRegister.dto.AdminDto;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    String userLogin(String username,String password,String identify);
    String studentRegister(StudentVO studentVO);
    String teacherRegister(TeacherVO teacherVO);
    String searchStudent(String stuNumber);
    String searchTeacher(String employeeNumber);
    String changePassword(AdminDto adminDto);
}
