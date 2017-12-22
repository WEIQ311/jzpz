package com.jzpz.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 用户模块功能角色
 * 通过角色Id和用户Id找到模块Id、或者通过组Id来找到模块Id
 *
 * @author weiQiang
 * @date 2016/10/8
 */
@Entity
@Table(name = "sys_module_role_fuc")
@Data
@RequiredArgsConstructor
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Builder
public class ModuleRoleFuc implements Serializable {

    private static final long serialVersionUID = 1956203423641776795L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long funcId;

    private Users fucUserId;

    private Module fucModuleId;

    private SysRole fucRoleId;

    private Group fucGroupId;

    private String comment;

    private Users insertUserId;

    private Date insertTime;

    private Users updateUserId;

    private Date updateTime;
}
