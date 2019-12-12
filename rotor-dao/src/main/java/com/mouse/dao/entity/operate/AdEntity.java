package com.mouse.dao.entity.operate;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author ; lidongdong
 * @Description 广告表
 * @Date 2019-11-26
 */
@Data
@Entity
@Table(name = "tbl_ad")
@org.hibernate.annotations.Table(appliesTo = "tbl_ad", comment = "广告表")
public class AdEntity implements Serializable {
    private static final long serialVersionUID = -5822032356734134747L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "\"name\"", columnDefinition = "varchar(64) COMMENT '广告标题'", nullable = false)
    private String name;

    @Column(name = "link", columnDefinition = "varchar(64) COMMENT '所广告的商品页面或者活动页面链接地址'", nullable = false)
    private String link;

    @Column(name = "url", columnDefinition = "varchar(255) COMMENT '广告宣传图片'", nullable = false)
    private String url;

    @Column(name = "position", columnDefinition = "tinyint unsigned DEFAULT '1' COMMENT '广告位置：1则是首页'", nullable = false)
    private Integer position;

    @Column(name = "content", columnDefinition = "varchar(255) COMMENT '活动内容'", nullable = false)
    private String content;

    @Column(name = "start_time", columnDefinition = "datetime COMMENT '广告开始时间'", nullable = false)
    private LocalDateTime startTime;

    @Column(name = "end_time", columnDefinition = "datetime COMMENT '广告结束时间'", nullable = false)
    private LocalDateTime endTime;

    @Column(name = "enabled", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '是否启动'", nullable = false)
    private Boolean enabled;


    @CreationTimestamp
    @Column(name = "add_time", columnDefinition = "datetime  COMMENT '创建时间'", nullable = false)
    private LocalDateTime addTime;

    @UpdateTimestamp
    @Column(name = "update_time", columnDefinition = "datetime COMMENT '更新时间'", nullable = false)
    private LocalDateTime updateTime;

   @Column(name = "deleted", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '逻辑删除 0 未删除，1 删除'", nullable = false)
    private Boolean deleted;

}