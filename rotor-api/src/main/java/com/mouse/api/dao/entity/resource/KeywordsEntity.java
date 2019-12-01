package com.mouse.api.dao.entity.resource;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


/**
 * @author ; lidongdong
 * @Description 热闹关键词表实体
 * @Date 2019-11-26
 */
@Data
@Entity
@Table(name = "tbl_keywords")
public class KeywordsEntity implements Serializable {

    private static final long serialVersionUID = -7424575062972591948L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "keyword", columnDefinition = "varchar(32) COMMENT '关键字'", nullable = false)
    private String keyword;
    @Column(name = "is_hot", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '是否热门'", nullable = false)
    private Integer isHot;
    @Column(name = "is_default", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '是否默认'", nullable = false)
    private Integer isDefault;
    @Column(name = "is_show", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '是否默认'", nullable = false)
    private Integer isShow;
    @Column(name = "sort_order", columnDefinition = "tinyint unsigned DEFAULT '50' COMMENT '排序'", nullable = false)
    private Integer sortOrder;
    @Column(name = "scheme_url", columnDefinition = "varchar(32) COMMENT '关键词的跳转链接'", nullable = false)
    private String schemeUrl;
    @Column(name = "type", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '类型'", nullable = false)
    private Integer type;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @CreationTimestamp
    @Column(name = "create_date")
    public Date createDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @UpdateTimestamp
    @Column(name = "update_date")
    public Date updateDate;
}
