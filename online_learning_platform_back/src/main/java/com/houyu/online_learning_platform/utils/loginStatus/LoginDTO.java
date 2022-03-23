package com.houyu.online_learning_platform.utils.loginStatus;

import com.houyu.online_learning_platform.back_stage_manage.entity.Student;
import com.houyu.online_learning_platform.back_stage_manage.entity.Teacher;
import com.houyu.online_learning_platform.back_stage_manage.vo.StudentVO;
import lombok.Data;

@Data
public class LoginDTO {
    private Integer code;
    private String message;
    private String result;
    private Student student;
    private Teacher teacher;
}
