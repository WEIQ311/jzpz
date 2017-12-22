package com.jzpz.service;

import com.jzpz.domain.HostConfig;
import com.jzpz.domain.Result;

/**
 * @author weiQiang
 */
public interface HostInfoService {
    /**
     * 搜索主机
     *
     * @param hostIp
     * @param hostName
     * @return
     */
    Result searchHost(String hostIp, String hostName);

    /**
     * 批量增加主机
     *
     * @param hostConfig
     * @param preview
     * @return
     */
    Result batchHost(HostConfig hostConfig, String preview);
}
