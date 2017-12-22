package com.jzpz.controller;

import com.jzpz.domain.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.PermitAll;

/**
 * 服务接口,不用于拦截
 *
 * @author weiQiang
 * @date 2016/10/1
 */
@PermitAll
@RestController
@RequestMapping(value = "serve")
public class ServeController {
    @RequestMapping(value = "getServe", method = RequestMethod.GET)
    public Result getServer() {
        return Result.builder().flag(true).message("").build();
    }
}
