package com.fitch.god.web.service.impl;

import com.finch.god.common.dao.MatchDetailMapper;
import com.finch.god.common.entity.MatchDetail;
import com.finch.god.common.entity.PlayerInfo;
import com.finch.god.common.service.IPlayerService;
import com.fitch.god.web.service.IRankService;
import com.fitch.god.web.vo.res.RankDataRes;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

@Service
public class IRankServiceImpl implements IRankService {

    @Resource
    private IPlayerService playerService;

    @Resource
    private MatchDetailMapper matchDetailMapper;


    @Override
    public List<RankDataRes> getRankData() {
        //1.获取选手Map
        Map<Integer, PlayerInfo> playerInfoMap = playerService.getPlayerMap();
        //2.查询排行榜汇总数据
        List<MatchDetail> matchDetailList = matchDetailMapper.countByPlay();
        //3.封装数据
        List<RankDataRes> resultList = new ArrayList<>();
        for (MatchDetail matchDetail : matchDetailList) {
            PlayerInfo playerInfo = playerInfoMap.get(matchDetail.getPlayerId());
            if(playerInfo == null){
                continue;
            }
            RankDataRes res = RankDataRes.builder()
                    .playId(playerInfo.getId())
                    .playName(playerInfo.getName())
                    .signature(playerInfo.getSignature())
                    .score(matchDetail.getScore()).build();
            resultList.add(res);
        }
        resultList.sort(Comparator.nullsLast(Comparator.comparing(RankDataRes::getScore).reversed()));
        return resultList;
    }
}
