package com.fitch.god.web.controller;

import com.finch.god.common.entity.UserInfo;
import com.fitch.god.web.service.userInfoService;
import com.fitch.god.web.service.userInfoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/userInfo")
public class userInfoController {

    @Autowired
    private userInfoService userInfoService;

    @RequestMapping("/getUserinfo")
    public String geUserInfo(Integer id){
        UserInfo userInfo = userInfoService.selectByPrimaryKey(id);
        return "index";
    }
}
