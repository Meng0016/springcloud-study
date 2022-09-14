package com.meng.controller;

import com.meng.entity.User;
import com.meng.service.UserSerivce;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class UserController {

    @Resource
    private UserSerivce userService;

    @RequestMapping("/user/{uid}")
    public User findUserById(@PathVariable("uid") Integer uid) {
        return userService.getUserById(uid);
    }

    @RequestMapping("/user/remain/{uid}")
    public int userRemain(@PathVariable("uid") int uid){
        return userService.getRemain(uid);
    }

    @RequestMapping("/user/borrow/{uid}")
    public boolean userBorrow(@PathVariable("uid") int uid){
        int remain = userService.getRemain(uid);
        return userService.setRemain(uid, remain - 1);
    }
}
