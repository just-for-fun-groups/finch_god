package com.fitch.god.web.service.impl;

import com.finch.god.common.dao.MatchInfoMapper;
import com.finch.god.common.dao.PlayerInfoMapper;
import com.finch.god.common.entity.MatchInfo;
import com.finch.god.common.entity.PlayerInfo;
import com.finch.god.common.utils.LocalDateUtils;
import com.fitch.god.web.service.MatchInfoService;
import com.fitch.god.web.service.PlayerInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchInfoServiceImpl implements MatchInfoService {

    @Autowired
    private MatchInfoMapper matchInfoMapper;



    @Override
    public List selectAll() {
        return matchInfoMapper.selectAll();
    }
}
