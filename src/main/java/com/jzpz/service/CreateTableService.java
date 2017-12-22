package com.jzpz.service;

import com.jzpz.domain.Result;

/**
 * @author weiQiang
 * @date 2016/9/24
 */
public interface CreateTableService {
    /**
     * 创建表格
     *
     * @param tableName
     * @param columns
     * @return
     */
    Result createTable(String tableName, String columns);
}
