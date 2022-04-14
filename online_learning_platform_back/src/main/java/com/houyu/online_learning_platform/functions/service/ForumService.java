package com.houyu.online_learning_platform.functions.service;

import com.houyu.online_learning_platform.functions.dto.ForumDto;
import com.houyu.online_learning_platform.functions.vo.ForumCommentVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public interface ForumService {
    void publishForum(MultipartFile[] files, String userNumber, String forumContent,String publishTime,String identify);
    List<ForumDto> getForumList(String userNumber,Pageable pageable);
    void addLike(Integer forumId,String userNumber,String identify);
    void cancelLike(Integer forumId, String userNumber);
    void addComments(ForumCommentVO forumCommentVO);
}
