package com.jzpz.service;

import com.jzpz.domain.MediaInfo;
import com.jzpz.domain.Result;

/**
 * @author Administrator
 * @date 2016/11/21
 */
public interface MediaInfoService {

    /**
     * 保存媒体信息
     *
     * @param mediaInfo
     * @return
     */
    Result saveMedia(MediaInfo mediaInfo);

    /**
     * 通过媒体ID获取媒体信息
     *
     * @param mediaId
     * @return
     */
    String findMediaById(Integer mediaId);

    /**
     * 获取所有媒体信息
     *
     * @return
     */
    Result getAllUploadImg();
}
