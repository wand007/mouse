package com.mouse.api.dao.entity.operate;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author ; lidongdong
 * @Description 活动
 * @Date 2019-11-26
 */
@Data
@Entity
@Table(name = "tbl_topic")
public class TopicEntity implements Serializable {

    private static final long serialVersionUID = -2877355312079771690L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "title", columnDefinition = "varchar(60) COMMENT '活动主题'", nullable = false)
    private String title;
    @Column(name = "content", columnDefinition = "text COMMENT '活动内容'", nullable = false)
    private String content;
    @Column(name = "avatar", columnDefinition = "varchar(255) COMMENT '化名'", nullable = false)
    private String avatar;
    @Column(name = "item_pic_url", columnDefinition = "varchar(255) COMMENT '活动条例图片'", nullable = false)
    private String itemPicUrl;
    @Column(name = "subtitle", columnDefinition = "varchar(60) COMMENT '子标题'", nullable = false)
    private String subtitle;
    @Column(name = "topic_category_id", columnDefinition = "int unsigned COMMENT '活动类别'", nullable = false)
    private Integer topicCategoryId;
    @Column(name = "actual_price", columnDefinition = "decimal(10,2) default '0' COMMENT '活动价格'", nullable = false)
    private BigDecimal priceInfo;
    @Column(name = "read_count", columnDefinition = "int unsigned COMMENT '阅读数量'", nullable = false)
    private Integer readCount;
    @Column(name = "scene_pic_url", columnDefinition = "varchar(60) COMMENT '场景图片链接'", nullable = false)
    private String scenePicUrl;
    @Column(name = "topic_template_id", columnDefinition = "int unsigned COMMENT '活动模板Id'", nullable = false)
    private Integer topicTemplateId;
    @Column(name = "topic_tag_id", columnDefinition = "int unsigned COMMENT '活动标签Id'", nullable = false)
    private Integer topicTagId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @CreationTimestamp
    @Column(name = "create_date")
    public Date createDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @UpdateTimestamp
    @Column(name = "update_date")
    public Date updateDate;
}
