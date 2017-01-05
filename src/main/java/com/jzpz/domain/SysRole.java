package com.jzpz.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 角色权限
 */
@Entity
@Table(name = "sys_role")
@Data
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = false, of = "id")
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Builder
public class SysRole implements Serializable{
   
	private static final long serialVersionUID = 6567394002301532940L;

	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    private String roleName;

    private String comment;

    private Date insertTime;

    private Date updateTime;

}


