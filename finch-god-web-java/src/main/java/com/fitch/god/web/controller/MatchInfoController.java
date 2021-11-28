package com.fitch.god.web.controller;

import com.finch.god.common.entity.PlayerInfo;
import com.finch.god.common.vo.ResultInfo;
import com.fitch.god.web.service.MatchInfoService;
import com.fitch.god.web.service.PlayerInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/getMatchInfo")
public class MatchInfoController {
    @Autowired
    private MatchInfoService matchInfoService;

    /**
     * 查询所有场次信息
     * @return
     */
    @RequestMapping("/getAllMatch")
    public ResultInfo getAllMatch(){
        List allPlayerList = matchInfoService.selectAll();
        return ResultInfo.success(allPlayerList);
    }

}
