package com.mouse.dao.entity.sys;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author ; lidongdong
 * @Description 文件存储表
 * @Date 2019-11-26
 */
@Data
@Entity
@Table(name = "tbl_storage")
public class StorageEntity implements Serializable {
    private static final long serialVersionUID = -6858387481483743467L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "\"key\"", columnDefinition = "varchar(32) COMMENT '文件的唯一索引'", nullable = false)
    private String key;

    @Column(name = "\"name\"", columnDefinition = "varchar(32) COMMENT '文件名'", nullable = false)
    private String name;

    @Column(name = "type", columnDefinition = "varchar(32) COMMENT '文件类型'", nullable = false)
    private String type;

    @Column(name = "size", columnDefinition = "int unsigned DEFAULT '0' COMMENT '文件大小'", nullable = false)
    private Integer size;

    @Column(name = "url", columnDefinition = "varchar(32) COMMENT '文件访问链接'", nullable = false)
    private String url;

    @CreationTimestamp
    @Column(name = "add_time", columnDefinition = "datetime  COMMENT '创建时间'", nullable = false)
    private LocalDateTime addTime;

    @UpdateTimestamp
    @Column(name = "update_time", columnDefinition = "datetime COMMENT '更新时间'", nullable = false)
    private LocalDateTime updateTime;

    @Column(name = "deleted", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '逻辑删除'", nullable = false)
    private Boolean deleted;
}