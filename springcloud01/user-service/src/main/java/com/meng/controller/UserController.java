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
        System.out.println("我被调用了");
        return userService.getUserById(uid);
    }
}
