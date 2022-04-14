package com.houyu.online_learning_platform.functions.service;

import com.houyu.online_learning_platform.functions.vo.BroadcastVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BroadcastService {
    List<BroadcastVO> getBroadcastList();
    BroadcastVO getBroadcastInfoById(Integer id);
    BroadcastVO getBroadcastInfoByNumber(String number);
}
