package com.jzpz.controller;

import com.jzpz.domain.Result;
import com.jzpz.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by weiQiang on 2016/10/1.
 */
@RestController
@RequestMapping(value = "user")
public class UserController {
    //用户登录Session
    public static final String USERSESSION_KEY = "userSessionKey";
    @Autowired
    private HttpSession session;

    //登录
    @RequestMapping(value = "login",method = RequestMethod.GET)
    public Result userLogin (HttpServletRequest request,String userName,String passWord){
        System.out.println("用户登录");
        System.out.println(Utils.getIpAddress(request));
        session.setAttribute(USERSESSION_KEY,"123456");
        return Result.builder().flag(true).message("").build();
    }
    //登出
    @RequestMapping(value = "logout",method = RequestMethod.GET)
    public Result userLogout (){
        System.out.println("用户登登出------------------");
        session.removeAttribute(USERSESSION_KEY);
        return Result.builder().flag(true).message("").build();
    }
}
