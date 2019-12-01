package com.mouse.api.dao.entity.user;


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
 * @Description 用户提现记录
 * @Date 2019-11-26
 */
@Data
@Entity
@Table(name = "tbl_user_level")
public class UserRecordEntity implements Serializable {

    private static final long serialVersionUID = 5572485228937073066L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer reportId;
    @Column(name = "mls_user_id", columnDefinition = "int unsigned default '0' COMMENT '分销用户ID'", nullable = false)
    private Integer mlsUserId;
    @Column(name = "order_id", columnDefinition = "varchar(32) COMMENT '订单ID'", nullable = false)
    private String orderId;
    @Column(name = "type", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '记录类型：1提现、2交易分成'", nullable = false)
    private Integer type;
    @Column(name = "price", columnDefinition = "decimal(10,2) default '0' COMMENT '金额单位分'", nullable = false)
    private BigDecimal price;
    @Column(name = "remarks", columnDefinition = "varchar(32) COMMENT '备注'", nullable = false)
    private String remarks;


    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @CreationTimestamp
    @Column(name = "create_date")
    public Date createDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @UpdateTimestamp
    @Column(name = "update_date")
    public Date updateDate;
}