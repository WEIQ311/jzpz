package com.jzpz.controller;

import com.jzpz.domain.MediaInfo;
import com.jzpz.domain.Result;
import com.jzpz.domain.Users;
import com.jzpz.service.MediaInfoService;
import com.jzpz.util.JzpzUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

/**
 * Created by Administrator on 2016/11/15.
 */
@RestController
@RequestMapping(value = "upload")
public class MediaInfoController {

    @Autowired
    private MediaInfoService mediaInfoService;

    @RequestMapping(value = "img",method = RequestMethod.POST)
    public Result uploadImg(MultipartFile file){
       // MultipartFile file = null;
        String fileStr = "";
        try {
           // file = (MultipartFile) new FileInputStream(files);
            fileStr = JzpzUtil.getBase64(file.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Users users = Users.builder().id(1).build();
       // System.out.println(files);
        System.out.println(file.getName());
        System.out.println(file.getOriginalFilename());
        System.out.println(file.getSize());
        System.out.println(file.getContentType());
        MediaInfo mediaInfo = MediaInfo.builder().mediaInfo("data:"+file.getContentType()+";base64,"+fileStr).mediaName(file.getOriginalFilename()).insertUser(users).updateUser(users).insertTime(new Date()).updateTime(new Date()).build();
        //System.out.println(fileStr);
        //data:image/jpeg;base64,
        //data:file.getContentType();base64,
        //video/x-ms-wmv
        //video/mp4
        return mediaInfoService.saveMedia(mediaInfo);
    }

    @RequestMapping(value = "findOne",method = RequestMethod.GET)
    public Result getMediaById(Integer mediaId){
        return Result.builder().flag(true).message(mediaInfoService.findMediaById(mediaId)).build();
    }

    @RequestMapping(value = "getAll",method = RequestMethod.GET)
    public Result getAllUploadImg(){
        return mediaInfoService.getAllUploadImg();
    }
}
