package com.jzpz.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 角色权限
 */
@Entity
@Table(name = "role")
@Data
@RequiredArgsConstructor
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Builder
public class Role implements Serializable{
   
	private static final long serialVersionUID = 6567394002301532940L;

	@Id
    @GeneratedValue
    private Long id;

    private String roleName;

    private String comment;

    private Users insertUser;

    private Date insertTime;

    private Users updateUser;

    private Date updateTime;

}


