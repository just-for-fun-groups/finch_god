package com.finch.god.common.service.impl;

import com.finch.god.common.dao.PlayerInfoMapper;
import com.finch.god.common.entity.PlayerInfo;
import com.finch.god.common.service.IPlayerService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class PlayerServiceImpl implements IPlayerService {

    @Resource
    private PlayerInfoMapper playerInfoMapper;


    /**
     * 获取选手id映射map
     */
    @Override
    public Map<Integer, PlayerInfo> getPlayerMap() {
        List<PlayerInfo> playerInfoList =  playerInfoMapper.selectAll();
        if(CollectionUtils.isEmpty(playerInfoList)){
            return new HashMap<>();
        }
        return playerInfoList.stream().collect(Collectors.toMap(PlayerInfo::getId, Function.identity()));
    }
}
