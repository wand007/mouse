package com.mouse.api.dao.entity.oms;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author ; lidongdong
 * @Description 公司收发货地址表
 * @Date 2019-11-26
 */
@Data
@Entity
@Table(name = "oms_company_address")
public class OmsCompanyAddressEntity implements Serializable {
    private static final long serialVersionUID = 2397236725158681528L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "address_name", columnDefinition = "varchar(64) COMMENT '地址名称'", nullable = false)
    private String addressName;

    @Column(name = "send_status", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '默认发货地址：0->否；1->是'", nullable = false)
    private Boolean sendStatus;

    @Column(name = "receive_status", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '是否默认收货地址：0->否；1->是'", nullable = false)
    private Boolean receiveStatus;

    @Column(name = "name", columnDefinition = "varchar(64) COMMENT '收发货人姓名'", nullable = false)
    private String name;

    @Column(name = "phone", columnDefinition = "varchar(64) COMMENT '收货人电话'", nullable = false)
    private String phone;

    @Column(name = "province", columnDefinition = "varchar(64) COMMENT '省/直辖市'", nullable = false)
    private String province;

    @Column(name = "city", columnDefinition = "varchar(64) COMMENT '市'", nullable = false)
    private String city;

    @Column(name = "region", columnDefinition = "varchar(64) COMMENT '区'", nullable = false)
    private String region;

    @Column(name = "detail_address", columnDefinition = "varchar(200) COMMENT '详细地址'", nullable = false)
    private String detailAddress;

}