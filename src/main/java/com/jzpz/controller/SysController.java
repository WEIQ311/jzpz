package com.jzpz.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 系统管理
 * Created by weiQiang on 2016/10/8.
 */
@RestController
@RequestMapping(value = "/sys")
public class SysController {

    @RequestMapping(value = "getDropdownMe",method = RequestMethod.GET)
    public String getDropdownMe(HttpServletRequest request){
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            System.out.println(auth.getAuthorities());
        }catch (Exception e){

        }
        return null;
    }
}
