package com.jzpz.domain;

import lombok.*;

import java.io.Serializable;

/**
 * 主机配置
 *
 * @author weiQiang
 */
@Data
@RequiredArgsConstructor
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Builder
public class HostConfig implements Serializable {

    private static final long serialVersionUID = 6578538438221312039L;

    public String ip;
    public String num1;
    public String num2;
    public String name1;
    public String name2;
    public String remark;
    public String userName;
    public String passWord;
    public String port;

}
