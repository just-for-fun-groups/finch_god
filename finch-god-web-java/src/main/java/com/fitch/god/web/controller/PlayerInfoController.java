package com.fitch.god.web.controller;

import com.finch.god.common.entity.PlayerInfo;
import com.finch.god.common.vo.ResultInfo;
import com.fitch.god.web.service.PlayerInfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/getPlayerInfo")
public class PlayerInfoController {
    @Autowired
    private PlayerInfoService playerInfoService;

    /**
     * 新增玩家信息
     * @param playerInfo
     * @return
     */
    @RequestMapping("/insertPlayerInfo")
    public ResultInfo insertPlayerInfo(@RequestBody PlayerInfo playerInfo){
        int i = playerInfoService.insertPlayerInfo(playerInfo);
        System.out.println("1111111");
        return ResultInfo.success("");

    }
    @RequestMapping("/getAllPlayer")
    public ResultInfo getAllPlayer(){
        List allPlayerList = playerInfoService.selectAll();
        return ResultInfo.success(allPlayerList);
    }
    @RequestMapping("/deletePlayerInfo")
    public ResultInfo deletePlayerInfo(@RequestBody PlayerInfo playerInfo){
        if (playerInfo!=null&&playerInfo.getId()!=null){
            Integer integer = playerInfoService.deletePlayerInfo(playerInfo.getId());
            if(integer==1){
                return ResultInfo.success("删除成功");
            }
        }
        return ResultInfo.success("操作异常,联系超越");

    }

}
