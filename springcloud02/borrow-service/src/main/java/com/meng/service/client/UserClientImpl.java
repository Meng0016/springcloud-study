package com.meng.service.client;

import com.meng.entity.User;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Author meng
 * @Data 2022/9/14 17:15
 */
@Component
public class UserClientImpl implements UserClient{
    @Override
    public User getUserById(Integer uid) {
        User user = new User();
        user.setName("我是替代方案");
        return user;
    }

    @Override
    public boolean userBorrow(int uid) {
        return false;
    }

    @Override
    public int userRemain(int uid) {
        return 0;
    }
}
