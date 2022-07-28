package com.meng.service.impl;

import com.meng.entity.Book;
import com.meng.mapper.BookMapper;
import com.meng.service.BookService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
@Service
public class BookServiceImpl implements BookService {

    @Resource
    private BookMapper bookMapper;

    @Override
    public Book getBookById(Integer bid) {
        return bookMapper.getBookById(bid);
    }
}
