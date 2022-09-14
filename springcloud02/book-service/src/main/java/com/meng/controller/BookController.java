package com.meng.controller;

import com.meng.entity.Book;
import com.meng.service.BookService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
public class BookController {

    @Resource
    private BookService bookService;

    @RequestMapping("/book/{bid}")
    public Book findBookById(@PathVariable("bid") Integer bid) {
        System.out.println("BookController.findBookById");  // 打印日志
        return bookService.getBookById(bid);
    }
    @RequestMapping("/book/remain/{bid}")
    public int bookRemain(@PathVariable("bid") int uid){
        return bookService.getRemain(uid);
    }

    @RequestMapping("/book/borrow/{bid}")
    public boolean bookBorrow(@PathVariable("bid") int uid){
        int remain = bookService.getRemain(uid);
        return bookService.setRemain(uid, remain - 1);
    }

}
