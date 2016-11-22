package com.jzpz.service.Impl;

import com.jzpz.domain.MediaInfo;
import com.jzpz.domain.Result;
import com.jzpz.repository.MediaInfoRepository;
import com.jzpz.service.MediaInfoService;
import com.jzpz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        //mediaInfoRepository.save(mediaInfo);
        return null;
    }

    @Override
    public String findMediaById(Integer mediaId) {
        return mediaInfoRepository.findOne(mediaId).getMediaInfo();
    }
}
