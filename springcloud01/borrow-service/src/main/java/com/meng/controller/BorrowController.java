package com.meng.controller;

import com.meng.entity.User;
import com.meng.entity.UserBorrowDetail;
import com.meng.service.BorrowService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Collections;

@RestController
public class BorrowController {
    @Resource
    private BorrowService borrowService;

//    @HystrixCommand(fallbackMethod = "onError")
    @RequestMapping("/borrow/{uid}")
    public UserBorrowDetail findUserBorrows(@PathVariable("uid") Integer uid) {
        return borrowService.getUserBorrowDetailById(uid);
    }

//    UserBorrowDetail onError(Integer uid){
//        System.out.println("执行了降级方法");
//        return new UserBorrowDetail(null, Collections.emptyList());
//    }
}
