package com.jzpz.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author weiQiang
 * @date 2016/10/8
 */
@Entity
@Table(name = "sys_group")
@Data
@RequiredArgsConstructor
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Builder
public class Group implements Serializable {

    private static final long serialVersionUID = -4348777688740242815L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long groupId;

    private String groupName;

    private String comments;

    private Users insertUserId;

    private Date insertTime;

    private Users updateUserId;

    private Date updateTime;

}
