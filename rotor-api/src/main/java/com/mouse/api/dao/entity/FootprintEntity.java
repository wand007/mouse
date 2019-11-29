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
@Table(name = "tbl_footprint")
public class FootprintEntity implements Serializable {

    private static final long serialVersionUID = 115187581014374552L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "user_id", columnDefinition = "int COMMENT '会员ID'", nullable = false)
    private Integer userId;
    @Column(name = "goods_id", columnDefinition = "varchar(32) COMMENT '商品id'", nullable = false)
    private Integer goodsId;
    @Column(name = "relay_id", columnDefinition = "varchar(32) COMMENT '转发人ID'", nullable = false)
    private String relayId;

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
