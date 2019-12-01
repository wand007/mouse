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
 * @Description 商品顶部轮播图
 * @Date 2019-11-26
 */
@Data
@Entity
@Table(name = "tbl_goods_gallery")
public class GoodsGalleryEntity implements Serializable {

    private static final long serialVersionUID = 2038160272383716418L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "goods_id", columnDefinition = "int unsigned default '0' COMMENT '商品id'", nullable = false)
    private Integer goodsId;
    @Column(name = "img_url", columnDefinition = "varchar(255) COMMENT '图片URL'", nullable = false)
    private String imgUrl;
    @Column(name = "img_desc", columnDefinition = "varchar(255) COMMENT '描述'", nullable = false)
    private String imgDesc;
    @Column(name = "sort_order", columnDefinition = "tinyint unsigned DEFAULT '50' COMMENT '排序'", nullable = false)
    private Integer sortOrder;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @CreationTimestamp
    @Column(name = "create_date")
    public Date createDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @UpdateTimestamp
    @Column(name = "update_date")
    public Date updateDate;
}
