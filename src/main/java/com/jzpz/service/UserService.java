package com.jzpz.service;

import com.jzpz.domain.Users;

/**
 * Created by weiQiang on 2016/10/7.
 */
public interface UserService {
    Users getUserByname(String username);
}
