package com.jzpz.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * Created by weiQiang on 2016/10/7.
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
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    private String userName;

    private String passWord;

    /**
     * 用户的权限
     */
    @Column(name = "role_id")
    private String roleId;

    private String groupId;

    private String realName;

    private String mobile;

    private Integer state;

    private String info;

    private String lastLoginIp;

    private Date lastLoginTime;
}
