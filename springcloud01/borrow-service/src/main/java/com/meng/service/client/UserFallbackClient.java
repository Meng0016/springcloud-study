package com.meng.service.client;

import com.meng.entity.User;
import com.meng.entity.UserBorrowDetail;
import org.springframework.stereotype.Component;

import java.util.Collections;
@Component
public class UserFallbackClient implements UserClient {

    @Override
    public User findUserById(Integer uid) {
        User user = new User();
        user.setName("降级用户");
        return user;
    }
}

