package com.jzpz.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Blob;
import java.util.Date;

/**
 * Created by Administrator on 2016/11/21.
 */
@Table(name = "media_info")
@Entity
@Data
@RequiredArgsConstructor
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Builder
public class MediaInfo implements Serializable {

    private static final long serialVersionUID = 5153003475632732455L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer mediaId;

    private String mediaName;
    /**
     * 媒体信息
     */
    private String mediaInfo;

    /**
     * 备注
     */
    private String remark;

    private Date insertTime;

    @ManyToOne
    private Users insertUser;

    @ManyToOne
    private Users updateUser;

    private Date updateTime;
}
