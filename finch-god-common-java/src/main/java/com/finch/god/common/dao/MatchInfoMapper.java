package com.finch.god.common.dao;

import com.finch.god.common.entity.MatchInfo;
import com.finch.god.common.entity.PlayerInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MatchInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(MatchInfo record);

    MatchInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MatchInfo record);

    List<MatchInfo> selectAll();

}