package com.jzpz.service.Impl;

import com.jzpz.domain.MediaInfo;
import com.jzpz.domain.Result;
import com.jzpz.repository.MediaInfoRepository;
import com.jzpz.service.MediaInfoService;
import com.jzpz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2016/11/21.
 */
@Service
public class MediaInfoServiceImpl implements MediaInfoService {

    @Autowired
    private MediaInfoRepository mediaInfoRepository;

    @Autowired
    private UserService userService;
    @Override
    public Result saveMedia(MediaInfo mediaInfo) {
        mediaInfo = mediaInfoRepository.save(mediaInfo);
        return Result.builder().message(mediaInfo.getMediaInfo()).flag(true).build();
    }

    @Override
    public String findMediaById(Integer mediaId) {
        return mediaInfoRepository.findOne(mediaId).getMediaInfo();
    }

    @Override
    public Result getAllUploadImg() {
        List<MediaInfo> mediaInfos = mediaInfoRepository.findAll();
        return Result.builder().data(mediaInfos).flag(true).build();
    }
}
