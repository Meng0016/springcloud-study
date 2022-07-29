package com.meng.service.impl;

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
import java.util.List;
import java.util.stream.Collectors;
@Service
public class BorrowServiceImpl implements BorrowService {
    @Resource
    private BorrowMapper borrowMapper;

//    @Resource
//    RestTemplate restTemplate;

    @Resource
    UserClient userClient;

    @Resource
    BookClient bookClient;

    @Override
    public UserBorrowDetail getUserBorrowDetailById(Integer uid) {
        List<Borrow> borrows= borrowMapper.getBorrowByUid(uid);
        User user = userClient.findUserById(uid);
        List<Book> bookList=borrows
                .stream()
                .map(borrow -> bookClient.findBookById(borrow.getBid()))
                .collect(Collectors.toList());
        return new UserBorrowDetail(user,bookList);
    }

}
