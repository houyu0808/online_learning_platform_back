package com.houyu.online_learning_platform.back_stage_manage.service;

import com.houyu.online_learning_platform.back_stage_manage.entity.Major;
import com.houyu.online_learning_platform.back_stage_manage.vo.MajorVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
public interface MajorService {
    //获取专业列表
    Page<Major> getMajorList(String majorName, Pageable pageable);
    //新建专业
    String saveMajor(MajorVO majorVO);
    //删除专业
    String deleteMajor(Integer[] ids);
    MajorVO getMajorById(Integer id);
    List<Major> getMajorByCollege(BigInteger collegeCode);
}
