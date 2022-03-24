package com.houyu.online_learning_platform.functions.dto;

import com.houyu.online_learning_platform.functions.vo.ForumCommentVO;
import com.houyu.online_learning_platform.functions.vo.ForumImgVO;
import com.houyu.online_learning_platform.functions.vo.ForumVO;
import lombok.Data;

import java.util.List;

@Data
public class ForumDto {
    private String headImg;
    private String username;
    private ForumVO forumVO;
    private List<ForumImgVO> forumImgVOList;
    private List<ForumCommentVO> forumCommentVOList;
    private boolean like;
}
