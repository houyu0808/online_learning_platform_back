package com.houyu.online_learning_platform.functions.controller;

import com.houyu.online_learning_platform.functions.service.BroadcastService;
import com.houyu.online_learning_platform.utils.responseMessage.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/broadcast")
public class BroadcastController {
    @Autowired
    private BroadcastService broadcastService;

    @GetMapping("/getbroadcastlist")
    public ResponseMessage getBroadcastList(){
        return ResponseMessage.ok(broadcastService.getBroadcastList());
    }

    @GetMapping("/getbroadcastbyid")
    public ResponseMessage getBroadcastById(@RequestParam Integer id){
        return ResponseMessage.ok(broadcastService.getBroadcastInfoById(id));
    }

    @GetMapping("/getbroadcastbynumber")
    public ResponseMessage getBroadcastByNumber(@RequestParam String number){
        return ResponseMessage.ok(broadcastService.getBroadcastInfoByNumber(number));
    }
}
