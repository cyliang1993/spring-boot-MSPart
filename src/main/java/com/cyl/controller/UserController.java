package com.cyl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cyl.domain.User;
import com.cyl.service.UserService;
import com.cyl.util.RandomValue;

/**
 * Created by xuliugen on 2016/5/4.
 */
@Controller
@RequestMapping(value = "/user", produces = {"application/json;charset=UTF-8"})
public class UserController {

    @Autowired
    private UserService userService;
    
    @ResponseBody
    @RequestMapping(value = "/getUser.do", method = RequestMethod.GET)
    public String select() {
       User user=new User();
    	//user.setName("zhang");	   
    	user = userService.getUser(1);
        return user.toString();
    }

    @ResponseBody
    @RequestMapping(value = "/addUser.do", method = RequestMethod.GET)
    public String add() {
        boolean isOk = userService.addUser(new User(RandomValue.getChineseName(),RandomValue.getPassword()));
        return isOk == true ? "SUCCESS！GREAT！" : "FAIL！";
    }
}
