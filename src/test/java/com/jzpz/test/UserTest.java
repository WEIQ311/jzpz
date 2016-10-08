package com.jzpz.test;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Created by weiQiang on 2016/10/8.
 */
public class UserTest {
    @Test
    public void getPwd(){
        //bcrypt算法与md5/sha算法有一个很大的区别，每次生成的hash值都是不同的，
        // 这样暴力猜解起来或许要更困难一些。同时加密后的字符长度比较长，有60位，
        // 所以用户表中密码字段的长度，如果打算采用bcrypt加密存储，字段长度不得低于60.
        String password = "bonc";
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);
        System.out.println(hashedPassword);
        System.out.println(hashedPassword.length());
        System.out.println("$2a$10$DhZBzfapM8CqY1I6gr6KSOiqTwKEhJbmYn5rSA3x6qZbV9E.Jnj.S".length());
    }
}
