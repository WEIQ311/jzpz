package com.jzpz.domain;

import lombok.*;

import java.io.Serializable;

/**
 * 创建表列基本信息
 * Created by weiQiang on 2016/9/24.
 */
@Data
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@RequiredArgsConstructor
@Builder
public class TableColumn implements Serializable{

    private static final long serialVersionUID = 1L;
    /**
     * 列中文名
     */
    private String columnChnName;
    /**
     * 列英文名
     */
    private String columnEngName;
    /**
     * 列类型
     */
    private String columnType;
    /**
     * 列长度
     */
    private String columnLength;
    /**
     * 是否主键
     */
    private boolean isPrimary;
    /**
     * 是否为空
     */
    private boolean isEmpty;
    /**
     * 是否唯一
     */
    private boolean isUnique;

}
