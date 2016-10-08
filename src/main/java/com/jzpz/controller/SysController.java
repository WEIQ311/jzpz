package com.jzpz.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 系统管理
 * Created by weiQiang on 2016/10/8.
 */
@RestController
@RequestMapping(value = "/sys")
public class SysController {
    /**
     * 根据用户权限获取用户的模块
     * @param request
     * @return
     */
    @RequestMapping(value = "getDropdownMe",method = RequestMethod.GET)
    public String getDropdownMe(HttpServletRequest request){
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            Object [] authortities = auth.getAuthorities().toArray();
            if (null!=authortities){
                String [] authortity = authortities[0].toString().split(",");
                for (int i = 0;i<authortity.length;i++){
                    System.out.println(authortity[i]);
                }
            }
        }catch (Exception e){

        }
        return null;
    }
}
