package com.houyu.online_learning_platform.back_stage_manage.service;

import com.houyu.online_learning_platform.back_stage_manage.entity.Class;
import com.houyu.online_learning_platform.back_stage_manage.vo.ClassVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ClassService {
    Page<Class> getClassList(String className, Pageable pageable);
    String saveClass(ClassVO classVO);
    String deleteClass(Integer[] ids);
    ClassVO getClassById(Integer id);
}
