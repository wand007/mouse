package com.mouse.api.dao.entity.operate;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


/**
 * @author ; lidongdong
 * @Description 拼团
 * @Date 2019-11-26
 */
@Data
@Entity
@Table(name = "tbl_group_buying")
public class GroupBuyingEntity implements Serializable {

    private static final long serialVersionUID = -9148499795535740936L;
    @Id
    @Column(name = "id", columnDefinition = "varchar(32) COMMENT '拼团ID'", nullable = false)
    private String id;
    @Column(name = "goods_id", columnDefinition = "int unsigned default '0' COMMENT '商品Id'", nullable = false)
    private Integer goodsId;
    @Column(name = "goods_name", columnDefinition = "varchar(64) COMMENT '商品名称'", nullable = false)
    private String goodsName;
    @Column(name = "group_num", columnDefinition = "int unsigned default '0' COMMENT '已经团购人数'", nullable = false)
    private Integer groupNum;
    @Column(name = "success_num", columnDefinition = "int unsigned default '0' COMMENT '成团需要人数'", nullable = false)
    private Integer successNum;
    @Column(name = "type", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '拼团状态 0未成功1已经拼团成功'", nullable = false)
    private Integer type;
    @Column(name = "merchant_id", columnDefinition = "varchar(32) COMMENT '供应商ID'", nullable = false)
    private String merchantId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @CreationTimestamp
    @Column(name = "create_date")
    public Date createDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @UpdateTimestamp
    @Column(name = "update_date")
    public Date updateDate;
}
