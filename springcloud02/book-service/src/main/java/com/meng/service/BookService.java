package com.meng.service;

import com.meng.entity.Book;

public interface BookService {

    Book getBookById(Integer bid);
    boolean setRemain(int bid, int count);
    int getRemain(int bid);

}
