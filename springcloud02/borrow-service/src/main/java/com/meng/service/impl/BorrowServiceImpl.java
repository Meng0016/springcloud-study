package com.meng.service.impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.meng.entity.Book;
import com.meng.entity.Borrow;
import com.meng.entity.User;
import com.meng.entity.UserBorrowDetail;
import com.meng.mapper.BorrowMapper;
import com.meng.service.BorrowService;
import com.meng.service.client.BookClient;
import com.meng.service.client.UserClient;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class BorrowServiceImpl implements BorrowService {
    @Resource
    private BorrowMapper borrowMapper;

    @Resource
    UserClient userClient;

    @Resource
    BookClient bookClient;


    @SentinelResource(value = "details", blockHandler = "blocked")
    @Override
    public UserBorrowDetail getUserBorrowDetailById(Integer uid) {
        List<Borrow> borrows= borrowMapper.getBorrowByUid(uid);
        User user = userClient.getUserById(uid);
        List<Book> bookList=borrows
                .stream()
                .map(borrow -> bookClient.getBookById(borrow.getBid()))
                .collect(Collectors.toList());
        return new UserBorrowDetail(user,bookList);
    }

    @GlobalTransactional
    @Override
    public boolean doBorrow(int uid, int bid) {
        System.out.println(RootContext.getXID());
        //1. 判断图书和用户是否都支持借阅
        if(bookClient.bookRemain(bid) < 1) {
            throw new RuntimeException("图书数量不足");
        }
        if(userClient.userRemain(uid) < 1) {
            throw new RuntimeException("用户借阅量不足");
        }
        //2. 首先将图书的数量-1
        if(!bookClient.bookBorrow(bid)) {
            throw new RuntimeException("在借阅图书时出现错误！");
        }
        //3. 添加借阅信息
        if(borrowMapper.getBorrow(uid, bid) != null) {
            throw new RuntimeException("此书籍已经被此用户借阅了！");
        }
        if(borrowMapper.addBorrow(uid, bid) <= 0) {
            throw new RuntimeException("在录入借阅信息时出现错误！");
        }
        //4. 用户可借阅-1
        if(!userClient.userBorrow(uid)) {
            throw new RuntimeException("在借阅时出现错误！");
        }
        //完成
        return true;
    }

    public UserBorrowDetail blocked(int uid, BlockException e) {
        return new UserBorrowDetail(null, Collections.emptyList());
    }

}
