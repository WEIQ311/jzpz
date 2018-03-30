package com.jzpz.controller;

import com.jzpz.domain.Pair;
import com.jzpz.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author weiQiang
 */
@RestController
@RequestMapping(value = "/email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @RequestMapping(value = "send", method = RequestMethod.GET)
    public String sendEmail() {
        String sendTo = "11**********@QQ.COM";
        String title = "测试邮件标题";
        String content = "测试邮件内容";
        Map<String, Object> contentMap = new HashMap<>();
        List<Pair<String, File>> attachments = new ArrayList<>();
        attachments.add(Pair.create("index.html",new File("E:\\IntelliJSpace\\jzpz\\src\\main\\resources\\resources\\index.html")));
        emailService.sendSimpleMail(sendTo, title, content);
        emailService.sendAttachmentsMail(sendTo, title, content,attachments);
        emailService.sendTemplateMail(sendTo, title, contentMap,attachments);
        return "haha";
    }
}
