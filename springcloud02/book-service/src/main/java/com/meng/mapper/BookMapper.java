package com.meng.mapper;

import com.meng.entity.Book;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Service;

@Mapper
public interface BookMapper {

    @Select("select * from db_book where bid = #{bid}")
    Book getBookById(Integer bid);

    @Select("select count from DB_BOOK  where bid = #{bid}")
    int getRemain(int bid);

    @Update("update DB_BOOK set count = #{count}  where bid = #{bid}")
    int setRemain(int bid, int count);

}
