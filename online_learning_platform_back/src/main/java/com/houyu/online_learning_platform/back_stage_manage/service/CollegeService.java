package com.houyu.online_learning_platform.back_stage_manage.service;

import com.houyu.online_learning_platform.back_stage_manage.entity.College;
import com.houyu.online_learning_platform.back_stage_manage.vo.CollegeVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.LongFunction;

@Service
public interface CollegeService {
    //获取学院列表
    Page<College> getCollegeList(String collegeName,Pageable pageable);
    //新建学院
    String saveCollege(CollegeVO collegeVO);
    //删除学院
    String deleteCollege(Integer[] ids);
    //根据id获取学院信息
    CollegeVO getCollegeById(Integer id);
}
