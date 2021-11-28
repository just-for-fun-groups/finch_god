package com.fitch.god.web.service.impl;

import com.finch.god.common.dao.PlayerInfoMapper;
import com.finch.god.common.entity.PlayerInfo;
import com.finch.god.common.utils.LocalDateUtils;
import com.fitch.god.web.service.PlayerInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PlayerInfoServiceImpl implements PlayerInfoService {

    @Autowired
    private PlayerInfoMapper playerInfoMapper;

    @Override
    public int insertPlayerInfo(PlayerInfo playerInfo){
        playerInfo.setCreateTime(LocalDateUtils.nowSecond());
        playerInfo.setUpdateTime(LocalDateUtils.nowSecond());

        return playerInfoMapper.insertSelective(playerInfo);


    }

    @Override
    public List selectAll() {
        return playerInfoMapper.selectAll();
    }

    @Override
    public Integer deletePlayerInfo(Integer id) {
        return playerInfoMapper.deleteByPrimaryKey(id);
    }
}
