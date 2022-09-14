package com.meng.service.client;

import com.meng.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "user-service",fallback= UserClientImpl.class)
public interface UserClient {

    @RequestMapping("/user/{uid}")
    User getUserById(@PathVariable("uid") Integer uid);

    @RequestMapping("/user/borrow/{uid}")
    boolean userBorrow(@PathVariable("uid") int uid);

    @RequestMapping("/user/remain/{uid}")
    int userRemain(@PathVariable("uid") int uid);
}
