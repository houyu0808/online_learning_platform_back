package com.houyu.online_learning_platform.functions.service.impl;

import com.houyu.online_learning_platform.back_stage_manage.dao.StudentRepository;
import com.houyu.online_learning_platform.back_stage_manage.dao.TeacherRepository;
import com.houyu.online_learning_platform.functions.dao.ForumCommentRepository;
import com.houyu.online_learning_platform.functions.dao.ForumImgRepository;
import com.houyu.online_learning_platform.functions.dao.ForumLikeRepository;
import com.houyu.online_learning_platform.functions.dao.ForumRepository;
import com.houyu.online_learning_platform.functions.entity.Forum;
import com.houyu.online_learning_platform.functions.service.ForumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;

@Service
@Transactional
public class ForumServiceImpl implements ForumService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private ForumRepository forumRepository;
    @Autowired
    private ForumCommentRepository forumCommentRepository;
    @Autowired
    private ForumImgRepository forumImgRepository;
    @Autowired
    private ForumLikeRepository forumLikeRepository;

    @Override
    public void publishForum(MultipartFile[] files, String userNumber, String forumContent,String publishTime,String identify) {
        Forum forum = new Forum();
        forum.setPublisherNumber(userNumber);
        forum.setForumContent(forumContent);
        forum.setPublishTime(publishTime);
        forum.setIdentify(identify);
    }
}
