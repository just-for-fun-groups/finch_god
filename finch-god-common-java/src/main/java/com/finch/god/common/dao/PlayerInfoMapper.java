package com.finch.god.common.dao;

import com.finch.god.common.entity.PlayerInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PlayerInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(PlayerInfo record);

    PlayerInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PlayerInfo record);

    List<PlayerInfo> selectAll();

}