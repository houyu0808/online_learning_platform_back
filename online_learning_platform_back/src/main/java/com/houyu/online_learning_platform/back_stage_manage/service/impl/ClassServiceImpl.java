package com.houyu.online_learning_platform.back_stage_manage.service.impl;

import com.houyu.online_learning_platform.back_stage_manage.dao.ClassRepository;
import com.houyu.online_learning_platform.back_stage_manage.dao.CollegeRepository;
import com.houyu.online_learning_platform.back_stage_manage.dao.MajorRepository;
import com.houyu.online_learning_platform.back_stage_manage.dao.StudentRepository;
import com.houyu.online_learning_platform.back_stage_manage.entity.Class;
import com.houyu.online_learning_platform.back_stage_manage.service.ClassService;
import com.houyu.online_learning_platform.back_stage_manage.vo.ClassVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ClassServiceImpl implements ClassService {
    @Autowired
    private ClassRepository classRepository;
    @Autowired
    private CollegeRepository collegeRepository;
    @Autowired
    private MajorRepository majorRepository;
    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Page<Class> getClassList(String className, Pageable pageable) {
        return classRepository.findByClassNameContaining(className,pageable);
    }

    @Override
    public String saveClass(ClassVO classVO) {
        if (classVO.getId() != null) {
            Optional<Class> classInfo = classRepository.findById(classVO.getId());
            Class classX;
            if(classInfo.isPresent()){
                classX = classInfo.get();
            }else{
                return "id不存在";
            }
            Class class1 = classRepository.findByClassName(classVO.getClassName());
            Class class2 =classRepository.findByClassCode(classVO.getClassCode());
            if(ObjectUtils.isEmpty(class1) && ObjectUtils.isEmpty(class2)){
                BeanUtils.copyProperties(classVO,classX);
                classRepository.save(classX);
                return "更新成功";
            }else{
                return "该班级名称/编码已存在";
            }
        }else{
            Class class1 = classRepository.findByClassName(classVO.getClassName());
            Class class2 =classRepository.findByClassCode(classVO.getClassCode());
            if(ObjectUtils.isEmpty(class1) && ObjectUtils.isEmpty(class2)){
                Class classInfo = new Class();
                classInfo.setClassName(classVO.getClassName());
                classInfo.setClassCode(classVO.getClassCode());
                classInfo.setAffiliatedCollegeCode(classVO.getAffiliatedCollegeCode());
                classInfo.setAffiliatedCollegeName(collegeRepository.findByCollegeCode(classVO.getAffiliatedCollegeCode()).getCollegeName());
                classInfo.setAffiliatedMajorCode(classVO.getAffiliatedMajorCode());
                classInfo.setAffiliatedMajorName(majorRepository.findByMajorCode(classVO.getAffiliatedMajorCode()).getMajorName());
                classRepository.save(classInfo);
                return "创建成功";
            }else if(!ObjectUtils.isEmpty(class1) && ObjectUtils.isEmpty(class2)){
                return "该班级名称已存在";
            }else if(ObjectUtils.isEmpty(class1) && !ObjectUtils.isEmpty(class2)){
                return "该班级编码已存在";
            }else{
                return "班级信息均已存在，创建失败";
            }
        }
    }

    @Override
    public String deleteClass(Integer[] ids) {
        for(Integer id:ids){
            Class classX = classRepository.findById(id).get();
            if(!ObjectUtils.isEmpty(classX)){
                classRepository.delete(classX);
                studentRepository.deleteAllByAffiliatedClassCode(classX.getClassCode());
            }
        }
        return "删除成功!";
    }
    @Override
    public ClassVO getClassById(Integer id){
        Class classX = classRepository.findById(id).get();
        ClassVO classVO = new ClassVO();
        BeanUtils.copyProperties(classX,classVO);
        return classVO;
    }

    @Override
    public List<Class> getClassByMajor(BigInteger majorCode) {
        return classRepository.getAllByAffiliatedMajorCode(majorCode);
    }

    @Override
    public List<Class> getClassByCollege(BigInteger collegeCode) {
        return classRepository.getAllByAffiliatedCollegeCode(collegeCode);
    }
}
