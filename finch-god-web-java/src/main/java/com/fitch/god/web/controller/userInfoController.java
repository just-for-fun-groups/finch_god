package com.fitch.god.web.controller;

import com.finch.god.common.entity.UserInfo;
import com.finch.god.common.vo.ResultInfo;
import com.fitch.god.web.service.userInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/userInfo")
public class userInfoController {

    @Autowired
    private userInfoService userInfoService;

    @RequestMapping("/getUserinfo")
    public ResultInfo geUserInfo(Integer id){
        UserInfo userInfo = userInfoService.selectByPrimaryKey(1);
        return ResultInfo.success(userInfo);
    }
}
