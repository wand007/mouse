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
@Table(name = "tbl_ad")
public class AdEntity implements Serializable {

    private static final long serialVersionUID = 2452866003502452213L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "ad_position_id", columnDefinition = "int COMMENT '广告位置Id'", nullable = false)
    private Integer adPositionId;
    @Column(name = "media_type", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '形式'", nullable = false)
    private Integer mediaType;
    @Column(name = "name", columnDefinition = "varchar(64) COMMENT '广告名称'", nullable = false)
    private String name;
    @Column(name = "link", columnDefinition = "varchar(255) COMMENT '链接'", nullable = false)
    private String link;
    @Column(name = "image_url", columnDefinition = "varchar(64) COMMENT '图片'", nullable = false)
    private String imageUrl;
    @Column(name = "content", columnDefinition = "varchar(64) COMMENT '内容'", nullable = false)
    private String content;
    @Column(name = "end_time", columnDefinition = "datetime COMMENT '结束时间'", nullable = false)
    private Date endTime;
    @Column(name = "enabled", columnDefinition = "tinyint  DEFAULT '1' COMMENT '状态'", nullable = false)
    private Integer enabled;


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
