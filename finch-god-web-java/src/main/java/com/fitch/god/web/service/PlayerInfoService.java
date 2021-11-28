package com.fitch.god.web.service;

import com.finch.god.common.entity.PlayerInfo;

import java.util.List;

public interface PlayerInfoService {
    public int insertPlayerInfo(PlayerInfo playerInfo);
    public List selectAll();
    public Integer deletePlayerInfo(Integer id);
}
