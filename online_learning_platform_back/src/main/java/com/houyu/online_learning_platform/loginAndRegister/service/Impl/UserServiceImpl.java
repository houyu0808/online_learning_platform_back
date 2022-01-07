package com.houyu.online_learning_platform.loginAndRegister.service.Impl;

import com.houyu.online_learning_platform.back_stage_manage.entity.Student;
import com.houyu.online_learning_platform.back_stage_manage.entity.Teacher;
import com.houyu.online_learning_platform.back_stage_manage.vo.StudentVO;
import com.houyu.online_learning_platform.back_stage_manage.vo.TeacherVO;
import com.houyu.online_learning_platform.loginAndRegister.dao.StudentsRepository;
import com.houyu.online_learning_platform.loginAndRegister.dao.TeachersRepository;
import com.houyu.online_learning_platform.loginAndRegister.dto.AdminDto;
import com.houyu.online_learning_platform.loginAndRegister.service.UserService;
import com.houyu.online_learning_platform.utils.token.GenerateToken;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

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
                if(student1 != null){
                    student = student1;
                }else{
                    student = student2;
                }
                if(student.getPassword().equals(password)){
                    jwt_token = GenerateToken.createToken(username,student.getId(),student.getIdentify());
                    httpServletResponse.addHeader("token",jwt_token);
                    httpServletResponse.setHeader("Access-Control-Expose-Headers","token");
                    return student.getUsername();
                }else{
                    return "1";
                }
            }else{
                return "2";
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
                    jwt_token = GenerateToken.createToken(username,teacher.getId(),teacher.getIdentify());
                    httpServletResponse.addHeader("token",jwt_token);
                    httpServletResponse.setHeader("Access-Control-Expose-Headers","token");
                    return teacher.getUsername();
                }else{
                    return "1";
                }
            }else{
                return "2";
            }
        }
    }

    @Override
    public String studentRegister(StudentVO studentVO) {
        Student student1 = studentRepository.findByStuNumber(studentVO.getStuNumber());
        Student student2 = studentRepository.findByPhoneNumber(studentVO.getPhoneNumber());
        if(student1 == null){
            return "学号不存在！请重新输入！";
        }else{
            if(student1.getUsername().equals(studentVO.getUsername())){
                if(student2 != null){
                    return "该手机号已被注册，请确认以后再输入!";
                }else{
                    if(student1.getPassword() != null && student1.getPhoneNumber() != null){
                        return "该学生信息已经完成注册！";
                    }else{
                        studentVO.setId(student1.getId());
                        studentVO.setIdentify(student1.getIdentify());
                        studentVO.setSex(student1.getSex());
                        studentVO.setAffiliatedClassCode(student1.getAffiliatedClassCode());
                        studentVO.setAffiliatedCollegeCode(student1.getAffiliatedCollegeCode());
                        studentVO.setAffiliatedMajorCode(student1.getAffiliatedMajorCode());
                        BeanUtils.copyProperties(studentVO,student1);
                        studentRepository.save(student1);
                        return "注册成功";
                    }
                }
            }else{
                return "学生信息错误!请重新输入!";
            }
        }
    }

    @Override
    public String teacherRegister(TeacherVO teacherVO) {
       Teacher teacher1 = teacherRepository.findByEmployeeNumber(teacherVO.getEmployeeNumber());
       Teacher teacher2 = teacherRepository.findByPhoneNumber(teacherVO.getPhoneNumber());
        if(teacher1 == null){
            return "工号不存在！请重新输入！";
        }else{
            if(teacher1.getUsername().equals(teacherVO.getUsername())){
                if(teacher2 != null){
                    return "该手机号已被注册，请确认以后再输入!";
                }else{
                    if(teacher1.getPassword() != null && teacher1.getPhoneNumber() != null){
                        return "该教师信息已经完成注册！";
                    }else{
                        teacherVO.setId(teacher1.getId());
                        teacherVO.setIdentify(teacher1.getIdentify());
                        teacherVO.setSex(teacher1.getSex());
                        teacherVO.setAffiliatedCollegeCode(teacher1.getAffiliatedCollegeCode());
                        BeanUtils.copyProperties(teacherVO,teacher1);
                        teacherRepository.save(teacher1);
                        return "注册成功";
                    }
                }
            }else{
                return "教师信息错误!请重新输入!";
            }
        }
    }

    @Override
    public String searchStudent(String stuNumber) {
        if(stuNumber == null || stuNumber == ""){
            return "1";
        }else{
            Student student = studentRepository.findByStuNumber(stuNumber);
            if(ObjectUtils.isEmpty(student)){
                return "2";
            }else if(student.getPassword() != null || student.getPhoneNumber() != null){
                return "3";
            }else{
                return student.getUsername();
            }
        }
    }

    @Override
    public String searchTeacher(String employeeNumber) {
        if(employeeNumber == null || employeeNumber == ""){
            return "1";
        }else{
            Teacher teacher = teacherRepository.findByEmployeeNumber(employeeNumber);
            if(ObjectUtils.isEmpty(teacher)){
                return "2";
            }else if(teacher.getPassword() != null || teacher.getPhoneNumber() != null){
                return "3";
            }else{
                return teacher.getUsername();
            }
        }
    }

    @Override
    public String changePassword(AdminDto adminDto) {
        if (adminDto.getIdentify().equals("学生")){
            Student student = studentRepository.findByStuNumber(adminDto.getUsername());
            if(student != null){
                if(student.getPhoneNumber() == null ||student.getPassword() == null){
                    return "该账号还未注册，请先完成注册";
                }else{
                    if(adminDto.getPassword().equals(student.getPassword())){
                        Student student1 = new Student();
                        BeanUtils.copyProperties(student,student1);
                        student1.setPassword(adminDto.getNewPassword());
                        studentRepository.save(student1);
                        return "密码修改成功";
                    }else{
                        return "旧密码错误，请重新输入";
                    }
                }
            }else{
                return "该学号不存在";
            }
        }else{
            Teacher teacher = teacherRepository.findByEmployeeNumber(adminDto.getUsername());
            if(teacher != null){
                if(teacher.getPhoneNumber() == null ||teacher.getPassword() == null){
                    return "该账号还未注册，请先完成注册";
                }else{
                    if(adminDto.getPassword().equals(teacher.getPassword())){
                        Teacher teacher1 = new Teacher();
                        BeanUtils.copyProperties(teacher,teacher1);
                        teacher1.setPassword(adminDto.getNewPassword());
                        teacherRepository.save(teacher1);
                        return "密码修改成功";
                    }else{
                        return "旧密码错误，请重新输入";
                    }
                }
            }else{
                return "该工号不存在";
            }
        }
    }

}
