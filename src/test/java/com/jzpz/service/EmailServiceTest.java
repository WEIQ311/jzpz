package com.jzpz.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmailServiceTest {
    @Autowired
    private EmailService emailService;
    @Test
    public void sendSimpleMail() {
        String sendTo = "weiqiang_6@126.com";
        String title = "测试邮件标题";
        String content = "测试邮件内容";
        emailService.sendSimpleMail(sendTo, title, content);
    }

    @Test
    public void sendAttachmentsMail() {
    }

    @Test
    public void sendTemplateMail() {
    }
}