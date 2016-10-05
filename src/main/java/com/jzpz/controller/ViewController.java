package com.jzpz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by weiQiang on 2016/10/2.
 */
@Controller
@RequestMapping(value = "view")
public class ViewController {
    @RequestMapping(value = "toLogin",method = RequestMethod.GET)
    public ModelAndView toLoginPage (HttpServletRequest request){
        System.out.println("进入登录界面");
        ModelAndView mav = new ModelAndView("/login.html");
        return mav;
    }
}
