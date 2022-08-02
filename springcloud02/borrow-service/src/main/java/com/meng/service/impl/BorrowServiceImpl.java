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

    public UserBorrowDetail blocked(int uid, BlockException e) {
        return new UserBorrowDetail(null, Collections.emptyList());
    }

}
