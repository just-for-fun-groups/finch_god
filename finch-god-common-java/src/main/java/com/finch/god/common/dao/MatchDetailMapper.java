package com.finch.god.common.dao;

import com.finch.god.common.entity.MatchDetail;

import java.util.List;

public interface MatchDetailMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(MatchDetail record);

    MatchDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MatchDetail record);

    List<MatchDetail> countByPlay();

}