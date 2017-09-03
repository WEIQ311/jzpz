package com.jzpz.service.Impl;

import com.jzpz.domain.HostConfig;
import com.jzpz.domain.HostInfo;
import com.jzpz.domain.Result;
import com.jzpz.service.HostInfoService;
import com.jzpz.util.JzpzUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service(value = "hostInfoService")
public class HostInfoServiceImpl implements HostInfoService {
    /**
     * 搜索主机
     *
     * @param hostIp
     * @param hostName
     * @return
     */
    @Override
    public Result searchHost(String hostIp, String hostName) {
        return null;
    }

    /**
     * 批量增加主机
     *
     * @param hostConfig
     * @param preview
     * @return
     */
    @Override
    public Result batchHost(HostConfig hostConfig, String preview) {
        Result result = JzpzUtil.bathHost(hostConfig);
        if(!Boolean.valueOf(preview)&&result.isFlag()){
            List<HostInfo> hostInfos = (List<HostInfo>) result.getData();
            hostInfos.forEach(hostInfo -> hostInfo.setHostId(UUID.randomUUID().toString()));
        }
        return result;
    }
}
