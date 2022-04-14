package com.houyu.online_learning_platform.functions.service.impl;

import com.houyu.online_learning_platform.back_stage_manage.dao.TeacherRepository;
import com.houyu.online_learning_platform.back_stage_manage.entity.Teacher;
import com.houyu.online_learning_platform.functions.dao.BroadcastRepository;
import com.houyu.online_learning_platform.functions.entity.Broadcast;
import com.houyu.online_learning_platform.functions.service.BroadcastService;
import com.houyu.online_learning_platform.functions.vo.BroadcastVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class BroadcastServiceImpl implements BroadcastService {
    @Autowired
    private BroadcastRepository broadcastRepository;
    @Autowired
    private TeacherRepository teacherRepository;

    @Override
    public List<BroadcastVO> getBroadcastList() {
        List<Broadcast> broadcastList = broadcastRepository.getBroadcastList();
        List<BroadcastVO> broadcastVOList = new ArrayList<>();
        for (Broadcast broadcast:broadcastList){
            BroadcastVO broadcastVO = new BroadcastVO();
            Teacher teacher = teacherRepository.findByEmployeeNumber(broadcast.getBroadcastNumber());
            broadcastVO.setHeadImgUrl(teacher.getHeadImgUrl());
            broadcastVO.setUsername(teacher.getUsername());
            broadcastVOList.add(broadcastVO);
        }
        return broadcastVOList;
    }

    @Override
    public BroadcastVO getBroadcastInfoById(Integer id) {
        Broadcast broadcast = broadcastRepository.findById(id).get();
        Teacher teacher = teacherRepository.findByEmployeeNumber(broadcast.getBroadcastNumber());
        BroadcastVO broadcastVO = new BroadcastVO();
        broadcastVO.setUsername(teacher.getUsername());
        broadcastVO.setHeadImgUrl(teacher.getHeadImgUrl());
        return broadcastVO;
    }

    @Override
    public BroadcastVO getBroadcastInfoByNumber(String number) {
        Broadcast broadcast = broadcastRepository.findByBroadcastNumber(number);
        Teacher teacher = teacherRepository.findByEmployeeNumber(broadcast.getBroadcastNumber());
        BroadcastVO broadcastVO = new BroadcastVO();
        broadcastVO.setUsername(teacher.getUsername());
        broadcastVO.setHeadImgUrl(teacher.getHeadImgUrl());
        return broadcastVO;
    }
}
