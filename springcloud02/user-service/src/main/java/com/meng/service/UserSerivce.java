package com.meng.service;

import com.meng.entity.User;

public interface UserSerivce {
    User getUserById(Integer uid);
    int getRemain(int uid);
    boolean setRemain(int uid, int count);
}
