package com.houyu.online_learning_platform.back_stage_manage.service.impl;

import com.houyu.online_learning_platform.back_stage_manage.dao.CourseRepository;
import com.houyu.online_learning_platform.back_stage_manage.dao.StudentRepository;
import com.houyu.online_learning_platform.back_stage_manage.dao.TeacherRepository;
import com.houyu.online_learning_platform.back_stage_manage.dao.VideoRepository;
import com.houyu.online_learning_platform.back_stage_manage.entity.Course;
import com.houyu.online_learning_platform.back_stage_manage.entity.Student;
import com.houyu.online_learning_platform.back_stage_manage.entity.Teacher;
import com.houyu.online_learning_platform.back_stage_manage.entity.Video;
import com.houyu.online_learning_platform.back_stage_manage.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CommonServiceImpl implements CommonService {
    @Autowired
    private VideoRepository videoRepository;
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private StudentRepository studentRepository;

    @Override
    public void addClickTimes(Integer id) {
        Video video = videoRepository.findById(id).get();
        Integer videoClickTimes = video.getClickTimes();
        if(videoClickTimes == null){
            video.setClickTimes(1);
        }else{
            video.setClickTimes(videoClickTimes + 1);
        }
        videoRepository.save(video);
        Teacher teacher = teacherRepository.findByEmployeeNumber(video.getBelongTeacherCode());
        Integer teacherClickTimes = teacher.getClickTimes();
        if(teacherClickTimes == null){
            teacher.setClickTimes(1);
        }else{
            teacher.setClickTimes(teacherClickTimes + 1);
        }
        teacherRepository.save(teacher);
        Course course = courseRepository.findByCourseCode(video.getBelongCourseCode());
        Integer courseClickTimes = course.getClickTimes();
        if(courseClickTimes == null){
            course.setClickTimes(1);
        }else{
            course.setClickTimes(courseClickTimes + 1);
        }
        courseRepository.save(course);
    }

    @Override
        public List<Video> getExtensionList() {
        return videoRepository.getAllByOrderByCreatedTimeDesc();
    }

    @Override
    public List<Video> getCarousel() {
        return videoRepository.getAllByClickTimes();
    }

    @Override
    public Student getStudentInformation(String username) {
        return studentRepository.findByUsername(username);
    }

    @Override
    public Teacher getTeacherInformation(String username) {
        return teacherRepository.findByUsername(username);
    }

    @Override
    public List<Video> getStudentHotList(BigInteger majorCode) {
        List<Course> courseList = courseRepository.findAllByAffiliatedMajorCode(majorCode);
        List<Video> videos = new ArrayList<>();
        for (Course course: courseList){
            List<Video> videoList = videoRepository.findAllByBelongCourseCode(course.getCourseCode());
            for(Video video:videoList){
                videos.add(video);
            }
        }
        return videos;
    }

    @Override
    public List<Video> getTeacherHotList(BigInteger collegeCode) {
        List<Course> courseList = courseRepository.findAllByAffiliatedCollegeCode(collegeCode);
        List<Video> videos = new ArrayList<>();
        for (Course course: courseList){
            List<Video> videoList = videoRepository.findAllByBelongCourseCode(course.getCourseCode());
            for(Video video:videoList){
                videos.add(video);
            }
        }
        return videos;
    }

    @Override
    public List<Video> getRecommendTeacher() {
        List<Teacher> teacherList = teacherRepository.findAllOrderByClickTimes();
        List<Video> videos = new ArrayList<>();
        for(Teacher teacher:teacherList){
            List<Video> videoList = videoRepository.findAllByBelongTeacherCodeOrderByClickTimes(teacher.getEmployeeNumber());
            for(Video video:videoList){
                videos.add(video);
                break;
            }
        }
        return videos;
    }
}
