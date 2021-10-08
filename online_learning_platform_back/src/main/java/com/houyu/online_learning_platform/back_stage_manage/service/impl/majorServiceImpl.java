package com.houyu.online_learning_platform.back_stage_manage.service.impl;

import com.houyu.online_learning_platform.back_stage_manage.dao.MajorRepository;
import com.houyu.online_learning_platform.back_stage_manage.entity.College;
import com.houyu.online_learning_platform.back_stage_manage.entity.Major;
import com.houyu.online_learning_platform.back_stage_manage.service.majorService;
import com.houyu.online_learning_platform.back_stage_manage.vo.MajorVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class majorServiceImpl implements majorService {
    @Autowired
    private MajorRepository majorRepository;

    //获取专业列表
    @Override
    public Page<Major> getMajorList(String majorName, Pageable pageable){
        Page<Major> majorPage = majorRepository.findByMajorNameContaining(majorName,pageable);
        return majorPage;
    }
    //新建专业
    @Override
    public String saveMajor(MajorVO majorVO){
        if (majorVO.getId() != null) {
            Optional<Major> majorInfo = majorRepository.findById(majorVO.getId());
            Major major;
            if(majorInfo.isPresent()){
                major = majorInfo.get();
            }else{
                return "id不存在";
            }
            BeanUtils.copyProperties(majorVO,major);
            majorRepository.save(major);
            return "更新成功!";
        }else{
            List<Major> major1 = majorRepository.findByMajorName(majorVO.getMajorName());
            List<Major> major2 =majorRepository.findByMajorCode(majorVO.getMajorCode());
            if(major1.isEmpty() && major2.isEmpty()){
                Major major = new Major();
                major.setMajorName(majorVO.getMajorName());
                major.setMajorCode(majorVO.getMajorCode());
                majorRepository.save(major);
                return "创建成功!";
            }else{
                return "该专业已存在";
            }
        }
    }
}
