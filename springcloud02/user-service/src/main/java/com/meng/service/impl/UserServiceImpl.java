package com.meng.service.impl;

import com.meng.entity.User;
import com.meng.mapper.UserMapper;
import com.meng.service.UserSerivce;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserSerivce {


    @Resource
    private UserMapper userMapper;

    @Override
    public User getUserById(Integer uid) {
        return userMapper.getUserById(uid);
    }

    @Override
    public int getRemain(int uid) {
        return userMapper.getUserBookRemain(uid);
    }

    @Override
    public boolean setRemain(int uid, int count) {
        return userMapper.updateBookCount(uid, count) > 0;
    }
}
