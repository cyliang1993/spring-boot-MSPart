package com.cyl.service;

import com.cyl.domain.User;

/**
 * Created by xuliugen on 2016/5/4.
 */
public interface UserService {

    public User getUser(int id);

    public boolean addUser(User user);
}
