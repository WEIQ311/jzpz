package com.jzpz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author weiQiang
 * @date 2016/10/2
 */
@Controller
public class ViewController {
    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public ModelAndView toLoginPage(HttpServletRequest request) {
        System.out.println("进入无权限界面");
        ModelAndView mav = new ModelAndView("/login.html");
        return mav;
    }
}
