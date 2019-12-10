package com.mouse.dao.entity.sys;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author ; lidongdong
 * @Description 系统配置表
 * @Date 2019-11-26
 */
@Data
@Entity
@Table(name = "tbl_system")
public class SystemEntity implements Serializable {
    private static final long serialVersionUID = -3621686985210451759L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "key_name", columnDefinition = "varchar(32) COMMENT '文件名'", nullable = false)
    private String keyName;

    @Column(name = "key_value", columnDefinition = "varchar(32) COMMENT '文件名'", nullable = false)
    private String keyValue;

    @CreationTimestamp
    @Column(name = "add_time", columnDefinition = "datetime  COMMENT '创建时间'", nullable = false)
    private LocalDateTime addTime;

    @UpdateTimestamp
    @Column(name = "update_time", columnDefinition = "datetime COMMENT '更新时间'", nullable = false)
    private LocalDateTime updateTime;

    @Column(name = "deleted", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '逻辑删除'", nullable = false)
    private Boolean deleted;
}