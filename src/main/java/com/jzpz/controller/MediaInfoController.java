package com.jzpz.controller;

import com.jzpz.domain.MediaInfo;
import com.jzpz.domain.Result;
import com.jzpz.domain.Users;
import com.jzpz.service.MediaInfoService;
import com.jzpz.util.JzpzUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private MediaInfoService mediaInfoService;

    @RequestMapping(value = "img",method = RequestMethod.POST)
    public Result uploadImg(MultipartFile file){
       if(null==file){
           return Result.builder().flag(false).message("上传文件为空").build();
       }
        String fileStr = "";
        try {
            fileStr = JzpzUtil.getBase64(file.getInputStream());
        } catch (IOException e) {
            log.error("上传文件出错:"+e.getMessage());
            return Result.builder().flag(false).message("上传文件出错:"+e.getMessage()).build();
        }
        log.info("上传图片,文件名:"+file.getOriginalFilename()+"大小:"+file.getSize()+"文件类型:"+file.getContentType());
        Users users = Users.builder().id(1).build();
        MediaInfo mediaInfo = MediaInfo.builder().mediaInfo("data:"+file.getContentType()+";base64,"+fileStr).mediaName(file.getOriginalFilename()).insertUser(users).updateUser(users).insertTime(new Date()).updateTime(new Date()).build();
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
