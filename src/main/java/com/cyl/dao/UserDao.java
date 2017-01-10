package com.cyl.dao;

import org.apache.ibatis.annotations.Mapper;
import com.cyl.domain.User;

@Mapper
public interface UserDao {

    //访问读数据库
    User getUser(int id);

    //访问写数据库
    int addUser(User user);

}
