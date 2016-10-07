package com.jzpz.domain;

import lombok.*;
import javax.persistence.Id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by weiQiang on 2016/10/7.
 */
@Entity
@Table(name = "authorities")
@Data
@RequiredArgsConstructor
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Builder
public class Authority implements Serializable{
    @Id
    @GeneratedValue
    private Long id;
    private String username;
    private String authority;
}
