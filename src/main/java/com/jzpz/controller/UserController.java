package com.jzpz.controller;

import com.jzpz.domain.Result;
import com.jzpz.domain.Users;
import com.jzpz.service.UserService;
import com.jzpz.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by weiQiang on 2016/10/1.
 */
@Controller
@RequestMapping(value = "user")
public class UserController {
    //用户登录Session
    public static final String USERSESSION_KEY = "userSessionKey";
    @Autowired
    private HttpSession session;
    @Autowired
    private UserService userService;
    //登录
    @RequestMapping(value = "login",method = RequestMethod.POST)
    public ModelAndView /*Result*/ userLogin (HttpServletRequest request,String username,String password,Model model){
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth instanceof AnonymousAuthenticationToken) {
                return new ModelAndView("/login.html"); //Result.builder().flag(false).message("用户不存在或密码错误").build();
            } else {
                //获取用户登录权限详细
                Object  pinciba=auth.getPrincipal();
                if(pinciba instanceof  UserDetails){
                    UserDetails userDetail = ((UserDetails) pinciba);
                    model.addAttribute("username", userDetail.getUsername());
                    Users u =userService.getUserByname(userDetail.getUsername());
                    session.setAttribute(USERSESSION_KEY,u.getId());
                    model.addAttribute("realName",u.getRealName());
                }
                //登录成功跳到主页
                System.out.println(Utils.getIpAddress(request));
                return  new ModelAndView("/index.html"); //Result.builder().flag(true).message(USERSESSION_KEY).build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return  new ModelAndView("/login.html"); //Result.builder().flag(false).message("用户不存在或密码错误").build();
        }
    }
}
