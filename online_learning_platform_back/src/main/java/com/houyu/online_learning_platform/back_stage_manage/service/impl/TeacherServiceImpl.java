package com.houyu.online_learning_platform.back_stage_manage.service.impl;

import com.houyu.online_learning_platform.back_stage_manage.dao.CollegeRepository;
import com.houyu.online_learning_platform.back_stage_manage.dao.TeacherRepository;
import com.houyu.online_learning_platform.back_stage_manage.entity.Teacher;
import com.houyu.online_learning_platform.back_stage_manage.service.TeacherService;
import com.houyu.online_learning_platform.back_stage_manage.vo.TeacherVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private CollegeRepository collegeRepository;

    @Override
    public Page<Teacher> getTeacherList(String username, Pageable pageable) {
        return teacherRepository.findByUsernameContaining(username,pageable);
    }

    @Override
    public String saveTeacher(TeacherVO teacherVO) {
        if (teacherVO.getId() != null) {
            Optional<Teacher> teacherInfo = teacherRepository.findById(teacherVO.getId());
            Teacher teacher;
            if (teacherInfo.isPresent()) {
                teacher = teacherInfo.get();
            } else {
                return "id不存在";
            }
            Teacher teacher2 = teacherRepository.findByEmployeeNumber(teacherVO.getEmployeeNumber());
            if (teacher2 == null) {
                teacherVO.setPassword(teacher.getPassword());
                teacherVO.setPhoneNumber(teacher.getPhoneNumber());
                teacherVO.setHeadImgUrl(teacher.getHeadImgUrl());
                BeanUtils.copyProperties(teacherVO, teacher);
                teacher.setAutograph(teacher2.getAutograph());
                teacherRepository.save(teacher);
                return "更新成功";
            }else{
                return "该教师工号已存在";
            }
        } else {
            Teacher teacher2 = teacherRepository.findByEmployeeNumber(teacherVO.getEmployeeNumber());
            if (teacher2 == null) {
                Teacher teacher = new Teacher();
                teacher.setIdentify(teacherVO.getIdentify());
                teacher.setUsername(teacherVO.getUsername());
                teacher.setSex(teacherVO.getSex());
                teacher.setEmployeeNumber(teacherVO.getEmployeeNumber());
                teacher.setAffiliatedCollegeCode(teacherVO.getAffiliatedCollegeCode());
                teacher.setAffiliatedCollegeName(collegeRepository.findByCollegeCode(teacherVO.getAffiliatedCollegeCode()).getCollegeName());
                teacher.setAutograph("这个人很懒,什么都没留下");
                teacherRepository.save(teacher);
                return "创建成功";
            } else {
                return "该教师工号已存在";
            }
        }
    }

    @Override
    public String deleteTeacher(Integer[] ids) {
        for (Integer id:ids){
            Teacher teacher= teacherRepository.findById(id).get();
            if (!ObjectUtils.isEmpty(teacher)) {
                teacherRepository.delete(teacher);
            }
        }
        return "删除成功！";
    }
    @Override
    public TeacherVO getTeacherById(Integer id){
        Teacher teacher = teacherRepository.findById(id).get();
        TeacherVO teacherVO = new TeacherVO();
        BeanUtils.copyProperties(teacher,teacherVO);
        return teacherVO;
    }
}
