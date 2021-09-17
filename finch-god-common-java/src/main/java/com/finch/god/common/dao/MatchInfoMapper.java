package com.finch.god.common.dao;

import com.finch.god.common.entity.MatchInfo;

public interface MatchInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(MatchInfo record);

    MatchInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MatchInfo record);

}