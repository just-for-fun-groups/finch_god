package com.fitch.god.web.controller;

import com.finch.god.common.vo.ResultInfo;
import com.fitch.god.web.service.IRankService;
import com.fitch.god.web.vo.res.RankDataRes;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/rank")
public class RankController {

    @Resource
    private IRankService rankService;

    /**
     * 获取排行榜数据
     */
    @RequestMapping(value = "/getRankData", method = RequestMethod.GET)
    public ResultInfo getRankData(){
        List<RankDataRes> resList = rankService.getRankData();
        return ResultInfo.success(resList);
    }

}
