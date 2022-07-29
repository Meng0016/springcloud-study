package com.meng.controller;

import com.meng.entity.Book;
import com.meng.service.BookService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


@RestController
public class BookController {

    @Resource
    private BookService bookService;

    @RequestMapping("/book/{bid}")
    public Book findBookById(@PathVariable("bid") Integer bid, HttpServletRequest request) {
        System.out.println(request.getHeader("Test"));
        return bookService.getBookById(bid);
    }

}
