package com.mouse.dao.entity.operate;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author ; lidongdong
 * @Description 专题表
 * @Date 2019-11-26
 */
@Data
@Entity
@Table(name = "tbl_topic")
public class TopicEntity implements Serializable {
    private static final long serialVersionUID = 3564013828603164479L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title", columnDefinition = "varchar(255) COMMENT '专题标题'", nullable = false)
    private String title;

    @Column(name = "subtitle", columnDefinition = "varchar(255) DEFAULT '' COMMENT '关键字'", nullable = false)
    private String subtitle;

    @Column(name = "price", columnDefinition = "decimal(10,2) default '0' COMMENT '专题相关商品最低价'", nullable = false)
    private BigDecimal price;

    @Column(name = "read_count", columnDefinition = "varchar(32) COMMENT '关键字'", nullable = false)
    private String readCount;

    @Column(name = "pic_url", columnDefinition = "varchar(32) COMMENT '关键字'", nullable = false)
    private String picUrl;

    @Column(name = "sort_order", columnDefinition = "tinyint unsigned DEFAULT '100' COMMENT '排序'", nullable = false)
    private Integer sortOrder;

    @Column(name = "goods", columnDefinition = "varchar(1023) DEFAULT '100' COMMENT '专题相关商品，采用JSON数组格式'", nullable = false)
    private String goods;


    @Column(name = "content", columnDefinition = "text COMMENT '专题内容，富文本格式'", nullable = false)
    private String content;

    @CreationTimestamp
    @Column(name = "add_time", columnDefinition = "datetime  COMMENT '创建时间'", nullable = false)
    private LocalDateTime addTime;

    @UpdateTimestamp
    @Column(name = "update_time", columnDefinition = "datetime COMMENT '更新时间'", nullable = false)
    private LocalDateTime updateTime;

   @Column(name = "deleted", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '逻辑删除 0 未删除，1 删除'", nullable = false)
    private Boolean deleted;
}