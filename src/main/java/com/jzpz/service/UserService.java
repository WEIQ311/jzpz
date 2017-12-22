package com.jzpz.service;

import com.jzpz.domain.Users;

/**
 * @author weiQiang
 * @date 2016/10/7
 */
public interface UserService {
    /**
     * 通过用户名获取用户信息
     *
     * @param username
     * @return
     */
    Users getUserByname(String username);
}
