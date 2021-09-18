package com.finch.god.common.service;

import com.finch.god.common.entity.PlayerInfo;

import java.util.Map;

public interface IPlayerService {

    Map<Integer, PlayerInfo> getPlayerMap();

}
