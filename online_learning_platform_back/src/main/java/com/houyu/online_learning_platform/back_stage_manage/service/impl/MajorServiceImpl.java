package com.houyu.online_learning_platform.back_stage_manage.service.impl;

import com.houyu.online_learning_platform.back_stage_manage.dao.*;
import com.houyu.online_learning_platform.back_stage_manage.entity.College;
import com.houyu.online_learning_platform.back_stage_manage.entity.Major;
import com.houyu.online_learning_platform.back_stage_manage.service.MajorService;
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
public class MajorServiceImpl implements MajorService {
    @Autowired
    private MajorRepository majorRepository;
    @Autowired
    private CollegeRepository collegeRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private ClassRepository classRepository;
    @Autowired
    private CourseRepository courseRepository;
    //获取专业列表
    @Override
    public Page<Major> getMajorList(String majorName, Pageable pageable){
        return majorRepository.findByMajorNameContaining(majorName,pageable);
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
            Major major1 = majorRepository.findByMajorName(majorVO.getMajorName());
            Major major2 =majorRepository.findByMajorCode(majorVO.getMajorCode());
            if(major1 == null && major2 == null){
                BeanUtils.copyProperties(majorVO,major);
                majorRepository.save(major);
                return "更新成功!";
            }else{
                return "该专业名称/编码已存在";
            }
        }else{
            Major major1 = majorRepository.findByMajorName(majorVO.getMajorName());
            Major major2 =majorRepository.findByMajorCode(majorVO.getMajorCode());
            if(major1 == null && major2 == null){
                Major major = new Major();
                major.setMajorName(majorVO.getMajorName());
                major.setMajorCode(majorVO.getMajorCode());
                major.setAffiliatedCollegeCode(majorVO.getAffiliatedCollegeCode());
                major.setAffiliatedCollegeName(collegeRepository.findByCollegeCode(majorVO.getAffiliatedCollegeCode()).getCollegeName());
                majorRepository.save(major);
                return "创建成功!";
            }else{
                return "该专业名称/编码已存在";
            }
        }
    }
    //删除专业
    @Override
    public String deleteMajor(Integer id){
        Optional<Major> majorInfo = majorRepository.findById(id);
        Major major;
        if(majorInfo.isPresent()){
            major = majorInfo.get();
            majorRepository.delete(major);
            studentRepository.deleteAllByAffiliatedMajorCode(major.getMajorCode());
            classRepository.deleteAllByAffiliatedMajorCode(major.getMajorCode());
            courseRepository.deleteAllByAffiliatedMajorCode(major.getMajorCode());
            return "删除成功!";
        }else{
            return "id不存在";
        }
    }
}
