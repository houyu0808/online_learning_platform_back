package com.houyu.online_learning_platform.back_stage_manage.service.impl;

import com.houyu.online_learning_platform.back_stage_manage.dao.ClassRepository;
import com.houyu.online_learning_platform.back_stage_manage.dao.CollegeRepository;
import com.houyu.online_learning_platform.back_stage_manage.dao.MajorRepository;
import com.houyu.online_learning_platform.back_stage_manage.dao.StudentRepository;
import com.houyu.online_learning_platform.back_stage_manage.entity.Major;
import com.houyu.online_learning_platform.back_stage_manage.entity.Student;
import com.houyu.online_learning_platform.back_stage_manage.service.StudentService;
import com.houyu.online_learning_platform.back_stage_manage.vo.StudentVO;
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
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private ClassRepository classRepository;
    @Autowired
    private CollegeRepository collegeRepository;
    @Autowired
    private MajorRepository majorRepository;

    @Override
    public Page<Student> getStudentList(String username, Pageable pageable){
        return studentRepository.findByUsernameContaining(username,pageable);
    }

    @Override
    public String saveStudent(StudentVO studentVO) {
        if (studentVO.getId() != null) {
            Optional<Student> studentInfo = studentRepository.findById(studentVO.getId());
            Student student;
            if(studentInfo.isPresent()){
                student = studentInfo.get();
            }else{
                return "id不存在";
            }
            Student studentX =studentRepository.findByStuNumber(studentVO.getStuNumber());
            if(studentX == null){
                studentVO.setStuNumber(student.getStuNumber());
                studentVO.setPassword(student.getPassword());
                BeanUtils.copyProperties(studentVO,student);
                studentRepository.save(student);
                return "更新成功!";
            }else {
                return "该学生学号已存在";
            }
        }else{
            Student studentX =studentRepository.findByStuNumber(studentVO.getStuNumber());
            if(studentX == null){
                Student student = new Student();
                student.setIdentify(studentVO.getIdentify());
                student.setUsername(studentVO.getUsername());
                student.setSex(studentVO.getSex());
                student.setStuNumber(studentVO.getStuNumber());
                student.setAffiliatedClassCode(studentVO.getAffiliatedClassCode());
                student.setAffiliatedClassName(classRepository.findByClassCode(studentVO.getAffiliatedClassCode()).getClassName());
                student.setAffiliatedCollegeCode(studentVO.getAffiliatedCollegeCode());
                student.setAffiliatedCollegeName(collegeRepository.findByCollegeCode(studentVO.getAffiliatedCollegeCode()).getCollegeName());
                student.setAffiliatedMajorCode(studentVO.getAffiliatedMajorCode());
                student.setAffiliatedMajorName(majorRepository.findByMajorCode(studentVO.getAffiliatedMajorCode()).getMajorName());
                studentRepository.save(student);
                return "创建成功!";
            }else{
                return "该学生学号已存在";
            }
        }
    }

    @Override
    public String deleteStudent(Integer id) {
        Optional<Student> studentInfo = studentRepository.findById(id);
        Student student;
        if(studentInfo.isPresent()){
            student = studentInfo.get();
            studentRepository.delete(student);
            return "删除成功!";
        }else{
            return "id不存在";
        }
    }
}
