package com.jzpz.domain;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * 主机管理
 *
 * @author weiQiang
 */
@Data
@RequiredArgsConstructor
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Builder
public class HostInfo implements Serializable {

    private static final long serialVersionUID = 869763239566390859L;
    public String hostId;
    public String hostIp;
    public String hostName;
    public String remark;
    public String userName;
    public String passWord;
    public String port;
    public Date insertTime;
    public Date updateTime;
}
