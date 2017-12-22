package com.jzpz.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @author weiQiang
 * @date 2016/10/10
 */
@Entity
@Table(name = "sys_table")
@Data
@RequiredArgsConstructor
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Builder
public class UserTable implements Serializable {

    private static final long serialVersionUID = -5129287490881240747L;

    @Id
    @GeneratedValue
    private Integer userId;

    private String userName;

    private Date insertTime;

    private Date updateTime;

    private Integer orderNum;
}
