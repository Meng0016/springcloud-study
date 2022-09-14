package com.meng.mapper;

import com.meng.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {
    @Select("select * from DB_USER where uid = #{uid}")
    User getUserById(int uid);

    @Select("select book_count from DB_USER where uid = #{uid}")
    int getUserBookRemain(int uid);

    @Update("update DB_USER set book_count = #{count} where uid = #{uid}")
    int updateBookCount(int uid, int count);

}
