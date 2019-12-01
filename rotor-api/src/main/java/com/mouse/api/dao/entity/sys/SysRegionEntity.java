package com.mouse.api.dao.entity.sys;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author ; lidongdong
 * @Description 省市县区域
 * @Date 2019-11-26
 */
@Data
@Entity
@Table(name = "tbl_sys_region")
public class SysRegionEntity extends Tree<SysRegionEntity> implements Serializable {

    private static final long serialVersionUID = 5782394031544812538L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "parent_id", columnDefinition = "int unsigned COMMENT '父节点'", nullable = false)
    private Integer parentId;
    @Column(name = "name", columnDefinition = "varchar(64) COMMENT '区域名称'", nullable = false)
    private String name;
    @Column(name = "type", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '类型 0国家 1省份 2地市 3区县'", nullable = false)
    private Integer type;
    @Column(name = "agency_id", columnDefinition = "int unsigned COMMENT '区域代理Id'", nullable = false)
    private Integer agencyId;

}
