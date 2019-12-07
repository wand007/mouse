package com.mouse.api.dao.entity.pms;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author ; lidongdong
 * @Description 画册图片表
 * @Date 2019-11-26
 */
@Data
@Entity
@Table(name = "pms_album_pic")
public class PmsAlbumPicEntity implements Serializable {
    private static final long serialVersionUID = 6255414696387057063L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "album_id", columnDefinition = "bigint(20) unsigned COMMENT '相册表id'", nullable = false)
    private Long albumId;
    @Column(name = "pic", columnDefinition = "varchar(500) COMMENT '展示图片'", nullable = false)
    private String pic;

}