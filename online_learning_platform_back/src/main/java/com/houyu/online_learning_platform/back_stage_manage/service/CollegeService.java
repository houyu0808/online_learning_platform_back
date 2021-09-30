package com.houyu.online_learning_platform.back_stage_manage.service;

import com.houyu.online_learning_platform.back_stage_manage.entity.College;
import com.houyu.online_learning_platform.back_stage_manage.vo.CollegeVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface CollegeService {
    //获取学院列表
    Page<College> getCollegeList(Pageable pageable);
    //新建学院
    void saveCollege(CollegeVO collegeVO);
}
