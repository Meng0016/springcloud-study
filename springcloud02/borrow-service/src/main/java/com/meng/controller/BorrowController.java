package com.meng.controller;

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
}
