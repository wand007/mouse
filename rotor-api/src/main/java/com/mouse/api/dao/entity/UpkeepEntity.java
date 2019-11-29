package com.mouse.api.dao.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;


/**
 * @author ; lidongdong
 * @Description 客户维护历史
 * @Date 2019-11-26
 */
@Data
@Entity
@Table(name = "tbl_upkeep")
public class UpkeepEntity implements Serializable {

    private static final long serialVersionUID = 1968732909350604282L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "customer_id", columnDefinition = "int default '0' COMMENT '客户id'", nullable = false)
    private Integer customerId;
    @Column(name = "ctype", columnDefinition = "varchar(60) COMMENT '维护方式'", nullable = false)
    private String ctype;
    @Column(name = "place", columnDefinition = "varchar(60) COMMENT '地点'", nullable = false)
    private String place;
    @Column(name = "status", columnDefinition = "tinyint unsigned DEFAULT '1' COMMENT '客户状态 1:有意向 2:无意向'", nullable = false)
    private Integer status;
    @Column(name = "gift_price", columnDefinition = "decimal(10,2) default '0' COMMENT '礼品金额'", nullable = false)
    private BigDecimal giftPrice;
    @Column(name = "uid", columnDefinition = "int default '0' COMMENT '修改人id(维护人id)'", nullable = false)
    private String uid;
    @Column(name = "data_from", columnDefinition = "tinyint unsigned DEFAULT '1' COMMENT '数据来源 1:用户购买商品,邮寄给客户 2:用户买了 服务性商品后，系统插入 3:用户自行维护'", nullable = false)
    private Integer dataFrom;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @CreationTimestamp
    @Column(name = "create_date")
    public Date createDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @UpdateTimestamp
    @Column(name = "update_date")
    public Date updateDate;
}
