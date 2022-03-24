package com.houyu.online_learning_platform.functions.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface ForumService {
    void publishForum(MultipartFile[] files, String userNumber, String forumContent,String publishTime,String identify);
}
