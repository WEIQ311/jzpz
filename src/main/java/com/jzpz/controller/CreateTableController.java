package com.jzpz.controller;

import com.jzpz.domain.Result;
import com.jzpz.service.CreateTableService;
import com.jzpz.util.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author weiQiang
 * @date 2016/9/24
 */
@RestController
@RequestMapping(value = "table")
public class CreateTableController {
    @Autowired
    private CreateTableService createTableService;

    @PreAuthorize("hasAnyAuthority('USER_USER')")
    @RequestMapping(value = "create", method = RequestMethod.POST)
    public String createTable(String tableName, String columns) {
        Result result = createTableService.createTable(tableName, columns);
        return JsonUtils.getJsonString4JavaPoje(result);
    }

    @RequestMapping(value = "getTable", method = RequestMethod.GET)
    public Result getServer() {
        return Result.builder().flag(true).message("getTable").build();
    }
}
