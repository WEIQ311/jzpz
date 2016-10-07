package com.jzpz.domain;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by weiQiang on 2016/10/7.
 */
@Entity
@Table(name = "users")
@Data
@RequiredArgsConstructor
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Builder
public class Users implements Serializable {
    @Id
    private Integer id;
    private String userName;
    private String passWord;
    private String role;

    private String realName;

    private String mobile;

    private Integer state;

    private String info;

}
