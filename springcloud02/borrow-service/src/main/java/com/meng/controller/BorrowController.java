package com.meng.controller;

import com.alibaba.fastjson.JSONObject;
import com.meng.entity.UserBorrowDetail;
import com.meng.service.BorrowService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class BorrowController {
    @Resource
    private BorrowService borrowService;


    @RequestMapping("/borrow/{uid}")
    public UserBorrowDetail findUserBorrows(@PathVariable("uid") Integer uid) {
        return borrowService.getUserBorrowDetailById(uid);
    }
    @RequestMapping("/borrow2/{uid}")
    public UserBorrowDetail findUserBorrows2(@PathVariable("uid") Integer uid) {
        return borrowService.getUserBorrowDetailById(uid);
    }
    @RequestMapping("/blocked")
    JSONObject blocked() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 403);
        jsonObject.put("success", false);
        jsonObject.put("message", "请求频率过快！");
        return jsonObject;
    }

}
