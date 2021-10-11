package com.houyu.online_learning_platform.back_stage_manage.service.impl;

import com.houyu.online_learning_platform.back_stage_manage.dao.ClassRepository;
import com.houyu.online_learning_platform.back_stage_manage.dao.CollegeRepository;
import com.houyu.online_learning_platform.back_stage_manage.dao.MajorRepository;
import com.houyu.online_learning_platform.back_stage_manage.entity.Class;
import com.houyu.online_learning_platform.back_stage_manage.service.ClassService;
import com.houyu.online_learning_platform.back_stage_manage.vo.ClassVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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

    @Override
    public Page<Class> getClassList(String className, Pageable pageable) {
        Page<Class> classPage = classRepository.findByClassNameContaining(className,pageable);
        return classPage;
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
            if(class1 == null && class2 == null){
                BeanUtils.copyProperties(classVO,classX);
                classRepository.save(classX);
                return "更新成功!";
            }else{
                return "该班级名称/编码已存在";
            }
        }else{
            Class class1 = classRepository.findByClassName(classVO.getClassName());
            Class class2 =classRepository.findByClassCode(classVO.getClassCode());
            if(class1 == null && class2 == null){
                Class classInfo = new Class();
                classInfo.setClassName(classVO.getClassName());
                classInfo.setClassCode(classVO.getClassCode());
                classInfo.setAffiliatedCollegeCode(classVO.getAffiliatedCollegeCode());
                classInfo.setAffiliatedCollegeName(collegeRepository.findByCollegeCode(classVO.getAffiliatedCollegeCode()).getCollegeName());
                classInfo.setAffiliatedMajorCode(classVO.getAffiliatedMajorCode());
                classInfo.setAffiliatedMajorName(majorRepository.findByMajorCode(classVO.getAffiliatedMajorCode()).getMajorName());
                classRepository.save(classInfo);
                return "创建成功!";
            }else{
                return "该班级名称/编码已存在";
            }
        }
    }

    @Override
    public String deleteClass(Integer id) {
        Optional<Class> classInfo = classRepository.findById(id);
        Class classX;
        if(classInfo.isPresent()){
            classX = classInfo.get();
            classRepository.delete(classX);
            return "删除成功!";
        }else{
            return "id不存在";
        }
    }
}
