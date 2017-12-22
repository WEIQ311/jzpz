package com.jzpz.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author weiQiang
 * @date 2016/10/7
 */
@Entity
@Table(name = "sys_user")
@Data
@RequiredArgsConstructor
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Builder
public class Users implements Serializable {

    private static final long serialVersionUID = -620221547485473029L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String userName;

    private String passWord;

    /**
     * 用户的权限
     */
    @JoinColumn(name = "role_id")
    @ManyToOne
    private SysRole roleId;

    private String groupId;

    private String realName;

    private String mobile;

    private Integer state;

    private String info;

    private String lastLoginIp;

    private Date lastLoginTime;
}
