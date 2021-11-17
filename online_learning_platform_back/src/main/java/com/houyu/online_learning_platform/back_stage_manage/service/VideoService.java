package com.houyu.online_learning_platform.back_stage_manage.service;

import com.houyu.online_learning_platform.back_stage_manage.entity.Video;
import com.houyu.online_learning_platform.back_stage_manage.vo.VideoVO;
import com.houyu.online_learning_platform.utils.responseMessage.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface VideoService {
    Page<Video> getVideoPage(String name, Pageable pageable);
    String deleteVideo(Integer id);
    String saveVideo(MultipartFile file1,MultipartFile file2,VideoVO videoVO);
}
