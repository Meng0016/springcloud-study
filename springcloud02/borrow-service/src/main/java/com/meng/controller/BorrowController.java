package com.meng.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.fastjson.JSONObject;
import com.meng.entity.User;
import com.meng.entity.UserBorrowDetail;
import com.meng.service.BorrowService;
import com.sun.deploy.security.BlockedException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Collections;

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
    @RequestMapping("/test")
    @SentinelResource(value = "test",
            fallback = "except",
            blockHandler = "blocked",//fallback指定出现异常时的替代方案
            exceptionsToIgnore = IOException.class)  //忽略那些异常，也就是说这些异常出现时不使用替代方案
    String test(){
//        throw new RuntimeException("HelloWorld！");
        return "test";
    }

    //替代方法必须和原方法返回值和参数一致，最后可以添加一个Throwable作为参数接受异常
    String except(Throwable t){
        return t.getMessage();
    }

    String blocked(BlockedException e){
        return "被限流了";
    }

    @RequestMapping("/test2")
    @SentinelResource("test2")   //注意这里需要添加@SentinelResource才可以，用户资源名称就使用这里定义的资源名称
    String findUserBorrows2(@RequestParam(value = "a", required = false) String a,
                            @RequestParam(value = "b", required = false) String b,
                            @RequestParam(value = "c",required = false) String c) {
        return "请求成功！a = "+a+", b = "+b+", c = "+c;
    }

    @RequestMapping("/borrow3/{uid}")
    @SentinelResource(value = "findUserBorrows2", blockHandler = "test")
    UserBorrowDetail findUserBorrows2(@PathVariable("uid") int uid) {
        throw new RuntimeException();
    }

    UserBorrowDetail test(int uid, BlockException e){
        System.out.println(e.getClass());
        return new UserBorrowDetail(new User(), Collections.emptyList());
    }

    @RequestMapping("/borrow/take/{uid}/{bid}")
    JSONObject borrow(@PathVariable("uid") int uid,
                      @PathVariable("bid") int bid){
        borrowService.doBorrow(uid, bid);

        JSONObject object = new JSONObject();
        object.put("code", "200");
        object.put("success", false);
        object.put("message", "借阅成功！");
        return object;
    }
}
