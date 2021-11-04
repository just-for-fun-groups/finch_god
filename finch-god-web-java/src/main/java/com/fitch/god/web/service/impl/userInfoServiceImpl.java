package com.fitch.god.web.service.impl;

import com.finch.god.common.dao.UserInfoMapper;
import com.finch.god.common.entity.UserInfo;
import com.fitch.god.web.service.userInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class userInfoServiceImpl implements userInfoService {

    @Resource
   private UserInfoMapper userInfoMapper;


    @Override
    public int deleteByPrimaryKey(Integer id) {
        return userInfoMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insertSelective(UserInfo record) {
        return 0;
    }

    @Override
    public UserInfo selectByPrimaryKey(Integer id) {
        return userInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(UserInfo record) {
        return 0;
    }
}
