package com.jzpz.domain;

import lombok.*;

import java.io.Serializable;

/**
 * 封装返回结果
 * Created by weiQiang on 2016/9/24.
 */
@Data
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@RequiredArgsConstructor
@Builder
public class Result implements Serializable{

    private static final long serialVersionUID = 1L;

    private  boolean flag = false;

    private String message = "";

    private Object data;

}
