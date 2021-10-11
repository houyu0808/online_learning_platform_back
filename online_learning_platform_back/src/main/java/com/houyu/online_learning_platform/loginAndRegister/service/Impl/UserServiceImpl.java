package com.houyu.online_learning_platform.loginAndRegister.service.Impl;

import com.houyu.online_learning_platform.back_stage_manage.entity.Student;
import com.houyu.online_learning_platform.back_stage_manage.entity.Teacher;
import com.houyu.online_learning_platform.back_stage_manage.vo.StudentVO;
import com.houyu.online_learning_platform.back_stage_manage.vo.TeacherVO;
import com.houyu.online_learning_platform.loginAndRegister.dao.StudentsRepository;
import com.houyu.online_learning_platform.loginAndRegister.dao.TeachersRepository;
import com.houyu.online_learning_platform.loginAndRegister.service.UserService;
import com.houyu.online_learning_platform.utils.token.generateToken;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private StudentsRepository studentRepository;
    @Autowired
    private HttpServletResponse httpServletResponse;
    @Autowired
    private TeachersRepository teacherRepository;

    @Override
    public String userLogin(String username, String password,String identify) {
        String jwt_token;
        if(identify.equals("学生")){
            Student student1 = studentRepository.findByPhoneNumber(username);
            Student student2 = studentRepository.findByStuNumber(username);
            if(student1 != null || student2 != null){
                Student student;
                if(student1 !=null){
                    student = student1;
                }else{
                    student = student2;
                }
                if(student.getPassword().equals(password)){
                    jwt_token = generateToken.createToken(username,student.getId(),student.getIdentify());
                    httpServletResponse.addHeader("auth-token",jwt_token);
                    return "登陆成功";
                }else
                {
                    return "密码错误!请重新输入!";
                }
            }else{
                return "用户名不存在!请重新输入!";
            }
        }else{
            Teacher teacher1 = teacherRepository.findByPhoneNumber(username);
            Teacher teacher2 = teacherRepository.findByEmployeeNumber(username);
            if (teacher1 != null || teacher2 != null){
                Teacher teacher;
                if(teacher1 !=null){
                    teacher = teacher1;
                }else{
                    teacher = teacher2;
                }
                if(teacher.getPassword().equals(password)){
                    jwt_token = generateToken.createToken(username,teacher.getId(),teacher.getIdentify());
                    httpServletResponse.addHeader("auth-token",jwt_token);
                    return "登陆成功";
                }else{
                    return "密码错误!请重新输入!";
                }
            }else{
                return "用户名不存在!请重新输入!";
            }
        }
    }

    @Override
    public String studentRegister(StudentVO studentVO) {
        Student student = studentRepository.findByStuNumber(studentVO.getStuNumber());
        if(student == null){
            return "学号不存在！请重新输入！";
        }else{
            if(student.getUsername().equals(studentVO.getUsername())){
                if(student.getPassword() != null && student.getPhoneNumber() != null){
                    return "该学生信息已经完成注册！";
                }else{
                    studentVO.setId(student.getId());
                    studentVO.setIdentify(student.getIdentify());
                    studentVO.setSex(student.getSex());
                    studentVO.setAffiliatedClassCode(student.getAffiliatedClassCode());
                    studentVO.setAffiliatedCollegeCode(student.getAffiliatedCollegeCode());
                    studentVO.setAffiliatedMajorCode(student.getAffiliatedMajorCode());
                    BeanUtils.copyProperties(studentVO,student);
                    studentRepository.save(student);
                    return "注册成功!";
                }
            }else{
                return "学生信息错误!请重新输入!";
            }
        }
    }

    @Override
    public String teacherRegister(TeacherVO teacherVO) {
       Teacher teacher = teacherRepository.findByEmployeeNumber(teacherVO.getEmployeeNumber());
        if(teacher == null){
            return "工号不存在！请重新输入！";
        }else{
            if(teacher.getUsername().equals(teacherVO.getUsername())){
                if(teacher.getPassword() != null && teacher.getPhoneNumber() != null){
                    return "该教师信息已经完成注册！";
                }else{
                    teacherVO.setId(teacher.getId());
                    teacherVO.setIdentify(teacher.getIdentify());
                    teacherVO.setSex(teacher.getSex());
                    teacherVO.setAffiliatedCollegeCode(teacher.getAffiliatedCollegeCode());
                    BeanUtils.copyProperties(teacherVO,teacher);
                    teacherRepository.save(teacher);
                    return "注册成功!";
                }
            }else{
                return "教师信息错误!请重新输入!";
            }
        }
    }

}