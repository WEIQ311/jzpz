package com.jzpz.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by weiQiang on 2016/10/8.
 */
@Entity
@Table(name = "sys_module")
@Data
@RequiredArgsConstructor
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Builder
public class Module implements Serializable{

	private static final long serialVersionUID = -4245516930648433162L;

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer moduleId;

    private String moduleName;

    private String moduleUrl;

    @Column(name = "module_role_id")
    private Role moduleRoles;

    private String comments;

    @Column(name = "insert_user_id")
    private Users insertUser;

    private Date insertTime;

    @Column(name = "update_user_id")
    private Users updateUser;

    private Date updateTime;
}
