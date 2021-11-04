package com.fitch.god.web.controller;

import com.finch.god.common.entity.PlayerInfo;
import com.finch.god.common.vo.ResultInfo;
import com.fitch.god.web.service.PlayerInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/getPlayerInfo")
public class PlayerInfoController {
    @Autowired
    private PlayerInfoService playerInfoService;
    @RequestMapping("/insertPlayerInfo")
    public ResultInfo insertPlayerInfo(@RequestBody PlayerInfo playerInfo){
        int i = playerInfoService.insertPlayerInfo(playerInfo);
        System.out.println("1111111");
        return ResultInfo.success("");

    }

}
