package com.houyu.online_learning_platform.functions.service.impl;

import antlr.StringUtils;
import com.houyu.online_learning_platform.back_stage_manage.dao.StudentRepository;
import com.houyu.online_learning_platform.back_stage_manage.dao.TeacherRepository;
import com.houyu.online_learning_platform.back_stage_manage.entity.Student;
import com.houyu.online_learning_platform.back_stage_manage.entity.Teacher;
import com.houyu.online_learning_platform.functions.dao.ForumCommentRepository;
import com.houyu.online_learning_platform.functions.dao.ForumImgRepository;
import com.houyu.online_learning_platform.functions.dao.ForumLikeRepository;
import com.houyu.online_learning_platform.functions.dao.ForumRepository;
import com.houyu.online_learning_platform.functions.dto.ForumDto;
import com.houyu.online_learning_platform.functions.entity.Forum;
import com.houyu.online_learning_platform.functions.entity.ForumComment;
import com.houyu.online_learning_platform.functions.entity.ForumImg;
import com.houyu.online_learning_platform.functions.entity.ForumLike;
import com.houyu.online_learning_platform.functions.service.ForumService;
import com.houyu.online_learning_platform.functions.vo.ForumCommentVO;
import com.houyu.online_learning_platform.functions.vo.ForumImgVO;
import com.houyu.online_learning_platform.functions.vo.ForumVO;
import io.netty.util.internal.StringUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class ForumServiceImpl implements ForumService {
    @Value("${oss.local.upload-file-path}")
    private String uploadFilePath;
    @Value("${path.uploadPath}")
    private String uploadPath;

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
        if(files.length == 0){
            forum.setImgJudge(0);
        }else{
            forum.setImgJudge(1);
        }
        forum.setVerifyString(UUID.randomUUID().toString());
        forum.setLikes(0);
        forum.setViewTimes(0);
        forumRepository.save(forum);
        Forum forum1 = forumRepository.findByVerifyString(forum.getVerifyString());
        if(forum1.getImgJudge() == 1){
            for(MultipartFile file:files){
                ForumImg forumImg = new ForumImg();
                String fileName = file.getOriginalFilename();
                String newName = UUID.randomUUID().toString() + fileName.substring(fileName.lastIndexOf("."), fileName.length());
                File dest = new File(uploadFilePath + "/forum/" + newName);
                forumImg.setImgUrl(uploadPath + "/forum/" + newName);
                forumImg.setBelongForumId(forum1.getId());
                try{
                    file.transferTo(dest);
                    forumImgRepository.save(forumImg);
                }catch (IOException e){
                    throw new Error("文件存储出错！请联系管理员！");
                }
            }
        }
    }

    @Override
    public List<ForumDto> getForumList(Pageable pageable) {
        Page<Forum> forumPage = forumRepository.findAllOrderBy(pageable);
        List<Forum> forumList = forumPage.getContent();
        List<ForumDto> forumDtoList = new ArrayList<>();
        for (Forum forum:forumList){
            ForumDto forumDto = new ForumDto();
            if(forum.getIdentify().equals("学生")){
                Student student = studentRepository.findByStuNumber(forum.getPublisherNumber());
                forumDto.setUsername(student.getUsername());
                forumDto.setHeadImg(student.getHeadImgUrl());
            }else{
                Teacher teacher = teacherRepository.findByEmployeeNumber(forum.getPublisherNumber());
                forumDto.setUsername(teacher.getUsername());
                forumDto.setHeadImg(teacher.getHeadImgUrl());
            }
            ForumLike forumLike = forumLikeRepository.findByBelongForumIdAndLikeUserNumber(forum.getId(),forum.getPublisherNumber());
            if(ObjectUtils.isEmpty(forumLike)){
                forumDto.setLike(false);
            }else{
                forumDto.setLike(true);
            }
            ForumVO forumVO = new ForumVO();
            BeanUtils.copyProperties(forum,forumVO);
            forumDto.setForumVO(forumVO);
            List<ForumComment> forumCommentList = forumCommentRepository.findAllByBelongForumId(forum.getId());
            List<ForumCommentVO> forumCommentVOList = new ArrayList<>();
            for(ForumComment forumComment:forumCommentList){
                ForumCommentVO forumCommentVO = new ForumCommentVO();
                BeanUtils.copyProperties(forumComment,forumCommentVO);
                if(forumCommentVO.getIdentify().equals("学生")){
                    Student student = studentRepository.findByStuNumber(forumCommentVO.getCommentUserNumber());
                    forumCommentVO.setUsername(student.getUsername());
                    forumCommentVO.setHeadImgUrl(student.getHeadImgUrl());
                }else{
                    Teacher teacher = teacherRepository.findByEmployeeNumber(forumCommentVO.getCommentUserNumber());
                    forumCommentVO.setUsername(teacher.getUsername());
                    forumCommentVO.setHeadImgUrl(teacher.getHeadImgUrl());
                }
                forumCommentVOList.add(forumCommentVO);
            }
            forumDto.setForumCommentVOList(forumCommentVOList);
            List<ForumImg> forumImgList = forumImgRepository.findAllByBelongForumId(forum.getId());
            List<ForumImgVO> forumImgVOList = new ArrayList<>();
            for(ForumImg forumImg:forumImgList){
                ForumImgVO forumImgVO = new ForumImgVO();
                BeanUtils.copyProperties(forumImg,forumImgVO);
                forumImgVOList.add(forumImgVO);
            }
            forumDto.setForumImgVOList(forumImgVOList);
            forumDtoList.add(forumDto);
        }
        return forumDtoList;
    }

    @Override
    public void addLike(Integer forumId, String userNumber) {
        Forum forum = forumRepository.findById(forumId).get();
        forum.setLikes(forum.getLikes() + 1);
        forumRepository.save(forum);
        ForumLike forumLike = new ForumLike();
        forumLike.setBelongForumId(forumId);
        forumLike.setLikeUserNumber(userNumber);
        forumLikeRepository.save(forumLike);
    }
    @Override
    public void cancelLike(Integer forumId, String userNumber) {
        Forum forum = forumRepository.findById(forumId).get();
        forum.setLikes(forum.getLikes() - 1);
        forumRepository.save(forum);
        ForumLike forumLike = forumLikeRepository.findByBelongForumIdAndLikeUserNumber(forumId,userNumber);
        forumLikeRepository.delete(forumLike);
    }

    @Override
    public void addComments(ForumCommentVO forumCommentVO) {
        ForumComment forumComment = new ForumComment();
        BeanUtils.copyProperties(forumCommentVO,forumComment);
        forumCommentRepository.save(forumComment);
    }
}
