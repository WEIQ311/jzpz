package com.jzpz.domain;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by weiQiang on 2016/9/25.
 */
@Entity
@Table(name = "sys_user")
@Data
@RequiredArgsConstructor
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Builder
public class SysUser implements Serializable {
    @Id
    @GeneratedValue(generator="uuid")
    @GenericGenerator(name="uuid", strategy="uuid")
    private String userId;
    private String userName;
    private String passWord;
    private Date insertTime;
    private Date updateTime;
}
