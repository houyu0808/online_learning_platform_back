package com.houyu.online_learning_platform.back_stage_manage.service.impl;

import com.houyu.online_learning_platform.back_stage_manage.dao.CourseRepository;
import com.houyu.online_learning_platform.back_stage_manage.dao.TeacherRepository;
import com.houyu.online_learning_platform.back_stage_manage.dao.VideoRepository;
import com.houyu.online_learning_platform.back_stage_manage.entity.Course;
import com.houyu.online_learning_platform.back_stage_manage.entity.Teacher;
import com.houyu.online_learning_platform.back_stage_manage.entity.Video;
import com.houyu.online_learning_platform.back_stage_manage.service.VideoService;
import com.houyu.online_learning_platform.back_stage_manage.vo.VideoVO;
import com.houyu.online_learning_platform.utils.responseMessage.Response;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.rmi.ServerException;
import java.util.*;


@Service
public class VideoServiceImpl implements VideoService {
    @Value("${oss.local.upload-file-path}")
    private String uploadFilePath;
    @Value("${path.uploadPath}")
    private String uploadPath;

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private VideoRepository videoRepository;

    @Override
    public Page<Video> getVideoPage(String name, Pageable pageable) {
        return videoRepository.findByNameContaining(name,pageable);
    }

    @Override
    public String deleteVideo(Integer[] ids) {
        for(Integer id : ids){
            Video video = videoRepository.findById(id).get();
            if(ObjectUtils.isEmpty(video)){
                throw new ServiceException("id不存在");
            }else{
                videoRepository.delete(video);
                try{
                    String imageString = video.getImageUrl().replace(uploadFilePath,uploadPath);
                    String videoString = video.getVideoUrl().replace(uploadFilePath,uploadPath);
                    File existFile1 = new File(imageString);
                    File existFile2 = new File(videoString);
                    if(existFile1.exists()){existFile1.delete();}
                    if(existFile2.exists()){existFile2.delete();}
                }catch (Exception e){
                    throw new Error("源文件不存在");
                }
            }
        }
        return "删除成功";
    }

    @Override
    public String saveVideo(MultipartFile file1,MultipartFile file2,VideoVO videoVO ) {
        if (file1.isEmpty() || file2.isEmpty()) {
            return "上传失败，请选择文件";
        }
        String fileName1 = file1.getOriginalFilename();
        String fileName2 = file2.getOriginalFilename();
        String newName1 = UUID.randomUUID().toString() + fileName1.substring(fileName1.lastIndexOf("."), fileName1.length());
        String newName2 = UUID.randomUUID().toString() + fileName2.substring(fileName2.lastIndexOf("."), fileName2.length());
        File dest1 = new File(uploadFilePath + "/images/" + newName1);
        File dest2 = new File(uploadFilePath + "/videos/" + newName2);
        if(videoVO.getId() != null){
            Video video = videoRepository.findByVideoCode(videoVO.getVideoCode());
            String imageString = video.getImageUrl().replace(uploadPath,uploadFilePath);
            String videoString = video.getVideoUrl().replace(uploadPath,uploadFilePath);
            File existFile1 = new File(imageString);
            File existFile2 = new File(videoString);
            if(existFile1.exists()){existFile1.delete();}
            if(existFile2.exists()){existFile2.delete();}
            copyVideo(videoVO,newName1,newName2);
            videoVO.setCreatedTime(video.getCreatedTime());
            videoVO.setClickTimes(video.getClickTimes());
            BeanUtils.copyProperties(videoVO,video);
            if(video.getVideoIntroduce() == null){
                video.setVideoIntroduce("-");
            }
            try {
                videoRepository.save(video);
                file1.transferTo(dest1);
                file2.transferTo(dest2);
                return "更新成功";
            } catch (IOException e) {
                return "更新失败";
            }
        }else{
            if(videoRepository.findByVideoCode(videoVO.getVideoCode()) != null){
                return "该视频编码已存在请重新创建";
            }else{
                copyVideo(videoVO,newName1,newName2);
                Date date = new Date();
                videoVO.setCreatedTime(date);
                Video video = new Video();
                BeanUtils.copyProperties(videoVO,video);
                video.setClickTimes(0);
                video.setVideoIntroduce("-");
                try {
                    videoRepository.save(video);
                    file1.transferTo(dest1);
                    file2.transferTo(dest2);
                    return "创建成功";
                } catch (IOException e) {
                    return "创建失败";
                }
            }

        }

    }
    //复制视频信息
    public void copyVideo(VideoVO videoVO,String newName1,String newName2){
        videoVO.setImageUrl(uploadPath +  "/images/" + newName1);
        videoVO.setVideoUrl(uploadPath + "/videos/" + newName2);
        Course course = courseRepository.findByCourseCode(videoVO.getBelongCourseCode());
        Teacher teacher = teacherRepository.findByEmployeeNumber(videoVO.getBelongTeacherCode());
        videoVO.setBelongCourseName(course.getCourseName());
        videoVO.setBelongTeacherName(teacher.getUsername());
    }

    @Override
    public VideoVO getVideoById(Integer id){
        Video video = videoRepository.findById(id).get();
        VideoVO videoVO = new VideoVO();
        BeanUtils.copyProperties(video,videoVO);
        return videoVO;
    }
}
