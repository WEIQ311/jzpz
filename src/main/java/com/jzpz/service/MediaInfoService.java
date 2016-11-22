package com.jzpz.service;

import com.jzpz.domain.MediaInfo;
import com.jzpz.domain.Result;

/**
 * Created by Administrator on 2016/11/21.
 */
public interface MediaInfoService {

    Result saveMedia(MediaInfo mediaInfo);


    String findMediaById(Integer mediaId);
}
