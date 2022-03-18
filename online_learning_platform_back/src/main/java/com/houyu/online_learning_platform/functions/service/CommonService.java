package com.houyu.online_learning_platform.functions.service;

import com.houyu.online_learning_platform.back_stage_manage.entity.Student;
import com.houyu.online_learning_platform.back_stage_manage.entity.Teacher;
import com.houyu.online_learning_platform.back_stage_manage.entity.Video;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
public interface CommonService {
    void addClickTimes(Integer id);
    List<Video> getExtensionList();
    List<Video> getCarousel();
    Student getStudentInformation(String username);
    Teacher getTeacherInformation(String username);
    List<Video> getStudentHotList(BigInteger majorCode);
    List<Video> getTeacherHotList(BigInteger collegeCode);
    List<Video> getRecommendTeacher();
    Video getVideoInfo(Integer id);
    List<Video> getVideoList(String teacherCode);
    Teacher getTeacherInfo(String teacherCode);
    List<Video> getTodayRecommend();
    Page<Video> searchVideo(String searchInfo, Pageable pageable);
}
