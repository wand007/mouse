package com.mouse.api.dao.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author ; lidongdong
 * @Description
 * @Date 2019-11-26
 */
@Data
@Entity
@Table(name = "tbl_category")
public class CategoryEntity implements Serializable {

    private static final long serialVersionUID = -3520787061524061771L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name", columnDefinition = "varchar(32) COMMENT '分类名称'", nullable = false)
    private String name;
    @Column(name = "keywords", columnDefinition = "varchar(32) COMMENT '关键字'", nullable = false)
    private String keywords;
    @Column(name = "front_desc", columnDefinition = "varchar(32) COMMENT '描述'", nullable = false)
    private String frontDesc;
    @Column(name = "parent_id", columnDefinition = "varchar(32) COMMENT '父节点'", nullable = false)
    private Integer parentId;
    @Column(name = "sort_order", columnDefinition = "tinyint unsigned DEFAULT '50' COMMENT '排序'", nullable = false)
    private Integer sortOrder;
    @Column(name = "show_index", columnDefinition = "tinyint DEFAULT '0' COMMENT '首页展示'", nullable = false)
    private Boolean showIndex;
    @Column(name = "is_show", columnDefinition = "tinyint unsigned DEFAULT '1' COMMENT '显示'", nullable = false)
    private Boolean isShow;
    @Column(name = "banner_url", columnDefinition = "varchar(64) COMMENT 'banner图片'", nullable = false)
    private String bannerUrl;
    @Column(name = "icon_url", columnDefinition = "varchar(64) COMMENT 'icon链接'", nullable = false)
    private String iconUrl;
    @Column(name = "img_url", columnDefinition = "varchar(64) COMMENT '图片'", nullable = false)
    private String imgUrl;
    @Column(name = "wap_banner_url", columnDefinition = "varchar(64) COMMENT '手机banner'", nullable = false)
    private String wapBannerUrl;
    @Column(name = "level", columnDefinition = "int DEFAULT '1' COMMENT '级别'", nullable = false)
    private String level;
    @Column(name = "type", columnDefinition = "tinyint  DEFAULT '0' COMMENT '类型'", nullable = false)
    private Integer type;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @CreationTimestamp
    @Column(name = "create_date")
    public Date createDate;
    //出参时间格式化
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @UpdateTimestamp
    @Column(name = "update_date")
    public Date updateDate;
}
