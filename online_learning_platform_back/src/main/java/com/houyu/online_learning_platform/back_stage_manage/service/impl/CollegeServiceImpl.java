package com.houyu.online_learning_platform.back_stage_manage.service.impl;

import com.houyu.online_learning_platform.back_stage_manage.dao.*;
import com.houyu.online_learning_platform.back_stage_manage.entity.College;
import com.houyu.online_learning_platform.back_stage_manage.service.CollegeService;
import com.houyu.online_learning_platform.back_stage_manage.vo.CollegeVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.NumberUtils;
import org.springframework.util.ObjectUtils;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CollegeServiceImpl implements CollegeService {
    @Autowired
    private CollegeRepository collegeRepository;
    @Autowired
    private MajorRepository majorRepository;
    @Autowired
    private ClassRepository classRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private TeacherRepository teacherRepository;

    //获取学院列表
    @Override
    public Page<College> getCollegeList(String collegeName,Pageable pageable){
        return collegeRepository.findByCollegeNameContaining(collegeName,pageable);
    }
    //新建、更新学院
    @Override
    public String saveCollege(CollegeVO collegeVO){
        if (collegeVO.getId() != null) {
            Optional<College> collegeInfo = collegeRepository.findById(collegeVO.getId());
            College college;
            if(collegeInfo.isPresent()){
                college = collegeInfo.get();
            }else{
                return "id不存在";
            }
            College college1 = collegeRepository.findByCollegeName(collegeVO.getCollegeName());
            College college2 =collegeRepository.findByCollegeCode(collegeVO.getCollegeCode());
            if(college1 == null && college2 == null){
                BeanUtils.copyProperties(collegeVO,college);
                collegeRepository.save(college);
                return "更新成功!";
            }else{
                return "该学院名称/编码已存在";
            }
        }else{
            College college1 = collegeRepository.findByCollegeName(collegeVO.getCollegeName());
            College college2 =collegeRepository.findByCollegeCode(collegeVO.getCollegeCode());
            if(college1 == null && college2 == null){
                College collegeInfo = new College();
                collegeInfo.setCollegeName(collegeVO.getCollegeName());
                collegeInfo.setCollegeCode(collegeVO.getCollegeCode());
                collegeRepository.save(collegeInfo);
                return "创建成功!";
            }else{
                return "该学院名称/编码已存在";
            }
        }
    }
    //删除学院
    @Override
    public String deleteCollege(Integer id){
        Optional<College> collegeInfo = collegeRepository.findById(id);
        College college;
        if(collegeInfo.isPresent()){
            college = collegeInfo.get();
            collegeRepository.delete(college);
            majorRepository.deleteAllByAffiliatedCollegeCode(college.getCollegeCode());
            classRepository.deleteAllByAffiliatedCollegeCode(college.getCollegeCode());
            studentRepository.deleteAllByAffiliatedCollegeCode(college.getCollegeCode());
            teacherRepository.deleteAllByAffiliatedCollegeCode(college.getCollegeCode());
            courseRepository.deleteAllByAffiliatedCollegeCode(college.getCollegeCode());
            return "删除成功!";
        }else{
            return "id不存在";
        }
    }
}
