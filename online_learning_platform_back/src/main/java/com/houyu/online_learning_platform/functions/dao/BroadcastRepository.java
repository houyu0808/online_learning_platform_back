package com.houyu.online_learning_platform.functions.dao;

import com.houyu.online_learning_platform.functions.entity.Broadcast;
import com.houyu.online_learning_platform.functions.vo.BroadcastVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BroadcastRepository extends JpaRepository<Broadcast,Integer> {
    @Query(nativeQuery = true, value = "select * from broadcast_list")
    List<Broadcast> getBroadcastList();

    Broadcast findByBroadcastNumber(String number);
}
