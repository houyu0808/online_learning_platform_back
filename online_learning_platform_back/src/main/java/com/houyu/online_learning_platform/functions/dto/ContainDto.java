package com.houyu.online_learning_platform.functions.dto;

import com.houyu.online_learning_platform.back_stage_manage.entity.Student;
import com.houyu.online_learning_platform.back_stage_manage.entity.Teacher;
import lombok.Data;

@Data
public class ContainDto {
    private Student student;
    private Teacher teacher;
}
