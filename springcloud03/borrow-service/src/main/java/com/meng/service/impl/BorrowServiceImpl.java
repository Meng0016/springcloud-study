package com.meng.service.impl;

import com.meng.entity.Book;
import com.meng.entity.Borrow;
import com.meng.entity.User;
import com.meng.entity.UserBorrowDetail;
import com.meng.mapper.BorrowMapper;
import com.meng.service.BorrowService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class BorrowServiceImpl implements BorrowService {
    @Resource
    private BorrowMapper borrowMapper;

    @Override
    public UserBorrowDetail getUserBorrowDetailById(Integer uid) {
        List<Borrow> borrows= borrowMapper.getBorrowByUid(uid);
        RestTemplate restTemplate = new RestTemplate();
        User user = restTemplate.getForObject("http://localhost:8101/user/"+uid, User.class);
        List<Book> bookList=borrows
                .stream()
                .map(borrow -> restTemplate.getForObject("http://localhost:8201/book/"+borrow.getBid(),Book.class))
                .collect(Collectors.toList());
        return new UserBorrowDetail(user,bookList);
    }

}
