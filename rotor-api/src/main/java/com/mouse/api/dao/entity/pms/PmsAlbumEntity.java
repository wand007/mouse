package com.mouse.api.dao.entity.pms;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author ; lidongdong
 * @Description 相册表
 * @Date 2019-11-26
 */
@Data
@Entity
@Table(name = "pms_album")
public class PmsAlbumEntity implements Serializable {
    private static final long serialVersionUID = -637814385776001240L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", columnDefinition = "varchar(64) COMMENT '相册名称'", nullable = false)
    private String name;
    @Column(name = "cover_pic", columnDefinition = "varchar(64) COMMENT '封面图片'", nullable = false)
    private String coverPic;
    @Column(name = "pic_count", columnDefinition = "int unsigned DEFAULT '0' COMMENT '图片数量'", nullable = false)
    private Integer picCount;
    @Column(name = "sort", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '排序'", nullable = false)
    private Integer sort;
    @Column(name = "description", columnDefinition = "varchar(64) COMMENT '描述'", nullable = false)
    private String description;

}