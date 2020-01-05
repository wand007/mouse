package com.mouse.dao.entity.user;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author ; lidongdong
 * @Description 收货地址表
 * @Date 2019-11-26
 */
@Data
@Entity
@Table(name = "tbl_address")
public class AddressEntity implements Serializable {
    private static final long serialVersionUID = -6342007520741031986L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "\"name\"", columnDefinition = "varchar(64) COMMENT '收货人名称'", nullable = false)
    private String name;

    @Column(name = "user_id", columnDefinition = "int unsigned COMMENT '用户表的用户ID'", nullable = false)
    private Integer userId;

    @Column(name = "province", columnDefinition = "varchar(32) COMMENT '省'", nullable = false)
    private String province;

    @Column(name = "city", columnDefinition = "varchar(32) COMMENT '城市'", nullable = false)
    private String city;

    @Column(name = "county", columnDefinition = "varchar(32) COMMENT '县/区'", nullable = false)
    private String county;

    @Column(name = "address", columnDefinition = "varchar(32) COMMENT '详细收货地址'", nullable = false)
    private String address;

    @Column(name = "area_code", columnDefinition = "varchar(32) COMMENT '地区编码'", nullable = false)
    private String areaCode;

    @Column(name = "postal_code", columnDefinition = "varchar(32) COMMENT '邮政编码'", nullable = false)
    private String postalCode;

    @Column(name = "tel", columnDefinition = "varchar(32) COMMENT '手机号码'", nullable = false)
    private String tel;

    @Column(name = "is_default", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '是否默认地址'", nullable = false)
    private Boolean isDefault;


    @CreationTimestamp
    @Column(name = "add_time", columnDefinition = "datetime  COMMENT '创建时间'", nullable = false)
    private LocalDateTime addTime;

    @UpdateTimestamp
    @Column(name = "update_time", columnDefinition = "datetime COMMENT '更新时间'", nullable = false)
    private LocalDateTime updateTime;

    @Column(name = "deleted", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '逻辑删除 0 未删除，1 删除'", nullable = false)
    private Boolean deleted;

    /**
     * 获取地址详情
     *
     * @return
     */
    public String onAddressDetail() {
        return this.province + this.city + this.county + this.address;
    }
}