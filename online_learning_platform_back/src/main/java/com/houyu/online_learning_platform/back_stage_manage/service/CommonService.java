package com.houyu.online_learning_platform.back_stage_manage.service;

import com.houyu.online_learning_platform.back_stage_manage.entity.Video;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommonService {
    void addClickTimes(Integer id);
    List<Video> getExtensionList();
    List<Video> getCarousel();
}
