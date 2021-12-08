package com.houyu.online_learning_platform.back_stage_manage.service.impl;

import com.houyu.online_learning_platform.back_stage_manage.dao.CollegeRepository;
import com.houyu.online_learning_platform.back_stage_manage.dao.CourseRepository;
import com.houyu.online_learning_platform.back_stage_manage.dao.MajorRepository;
import com.houyu.online_learning_platform.back_stage_manage.entity.College;
import com.houyu.online_learning_platform.back_stage_manage.entity.Course;
import com.houyu.online_learning_platform.back_stage_manage.service.CourseService;
import com.houyu.online_learning_platform.back_stage_manage.vo.CourseVO;
import io.netty.util.internal.ObjectUtil;
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
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private CollegeRepository collegeRepository;
    @Autowired
    private MajorRepository majorRepository;

    @Override
    public Page<Course> getCourseList(String courseName, Pageable pageable) {
        return courseRepository.findByCourseNameContaining(courseName,pageable);
    }

    @Override
    public String saveCourse(CourseVO courseVO) {
        if (courseVO.getId() != null) {
            Optional<Course> courseInfo = courseRepository.findById(courseVO.getId());
            Course course;
            if(courseInfo.isPresent()){
                course = courseInfo.get();
            }else{
                return "id不存在";
            }
            Course courseX =courseRepository.findByCourseCode(courseVO.getCourseCode());
            if(courseX == null){
                BeanUtils.copyProperties(courseVO,course);
                courseRepository.save(course);
                return "更新成功";
            }else{
                return "该课程编码已存在";
            }
        }else{
            Course course =courseRepository.findByCourseCode(courseVO.getCourseCode());
            if(course == null){
                Course courseInfo = new Course();
                courseInfo.setCourseCode(courseVO.getCourseCode());
                courseInfo.setAffiliatedCollegeCode(courseVO.getAffiliatedCollegeCode());
                courseInfo.setAffiliatedCollegeName(collegeRepository.findByCollegeCode(courseVO.getAffiliatedCollegeCode()).getCollegeName());
                courseInfo.setAffiliatedMajorCode(courseVO.getAffiliatedMajorCode());
                courseInfo.setAffiliatedMajorName(majorRepository.findByMajorCode(courseVO.getAffiliatedMajorCode()).getMajorName());
                courseInfo.setTeacher(courseVO.getTeacher());
                courseInfo.setStatus(courseVO.getStatus());
                courseRepository.save(courseInfo);
                return "创建成功";
            }else{
                return "该课程编码已存在";
            }
        }
    }

    @Override
    public String deleteCourse(Integer[] ids) {
        for(Integer id: ids){
            Course course = courseRepository.findById(id).get();
            if(!ObjectUtils.isEmpty(course)){
                courseRepository.delete(course);
            }
        }
        return "删除成功";
    }

    @Override
    public CourseVO getCourseById(Integer id){
        Course course = courseRepository.findById(id).get();
        CourseVO courseVO = new CourseVO();
        BeanUtils.copyProperties(course,courseVO);
        return courseVO;
    }

    @Override
    public List<Course> getCourseByMajor(BigInteger majorCode) {
        return courseRepository.findAllByAffiliatedMajorCode(majorCode);
    }
}
