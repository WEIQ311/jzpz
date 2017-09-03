package com.jzpz.controller;

import com.jzpz.domain.HostConfig;
import com.jzpz.domain.Result;
import com.jzpz.service.HostInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "hostInfo")
public class HostInfoController {

    @Autowired
    private HostInfoService hostInfoService;

    @RequestMapping(value = "searchHost",method = RequestMethod.POST)
    public Result searchHost(String hostIp,String hostName){
        return hostInfoService.searchHost(hostIp,hostName);
    }

    @RequestMapping(value = "batchHost",method = RequestMethod.POST)
    public Result batchHost(HostConfig hostConfig,String preview){
        return hostInfoService.batchHost(hostConfig,preview);
    }

}
