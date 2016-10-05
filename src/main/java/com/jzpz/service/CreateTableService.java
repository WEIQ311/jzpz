package com.jzpz.service;

import com.jzpz.domain.Result;

/**
 * Created by weiQiang on 2016/9/24.
 */
public interface CreateTableService {
    Result createTable(String tableName, String columns);
}
