package com.cyl.dao;

import org.apache.ibatis.annotations.Mapper;
import com.cyl.domain.User;

public interface UserMapper {

    //访问读数据库
    User getUser(int id);

    //访问写数据库
    int addUser(User user);

}
