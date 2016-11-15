package com.jzpz.controller;

import com.jzpz.util.JzpzUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Administrator on 2016/11/15.
 */
@RestController
@RequestMapping(value = "upload")
public class UploadImg {
    @RequestMapping(value = "img",method = RequestMethod.POST)
    public String uploadImg(String files){
        MultipartFile file = null;
        String fileStr = "";
        try {
            file = (MultipartFile) new FileInputStream(files);
            fileStr = JzpzUtil.getBase64(file.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(files);
        System.out.println(file.getName());
        System.out.println(file.getOriginalFilename());
        System.out.println(file.getSize());
        System.out.println(file.getContentType());
        //System.out.println(fileStr);
        //data:file.getContentType();base64,
        return fileStr;
    }
}
