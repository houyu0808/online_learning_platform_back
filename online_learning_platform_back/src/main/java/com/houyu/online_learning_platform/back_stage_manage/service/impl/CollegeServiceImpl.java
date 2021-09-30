package com.houyu.online_learning_platform.back_stage_manage.service.impl;

import com.houyu.online_learning_platform.back_stage_manage.dao.CollegeRepository;
import com.houyu.online_learning_platform.back_stage_manage.entity.College;
import com.houyu.online_learning_platform.back_stage_manage.service.CollegeService;
import com.houyu.online_learning_platform.back_stage_manage.vo.CollegeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class CollegeServiceImpl implements CollegeService {
    @Autowired
    private CollegeRepository collegeRepository;

    //获取学院列表
    @Override
    public Page<College> getCollegeList(Pageable pageable){
        Page<College> collegePage = collegeRepository.findAll(pageable);
        return collegePage;
    }
    //新建学院
    @Override
    public void saveCollege(CollegeVO collegeVO){

    }
}
