package com.mouse.api.dao.entity.ums;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author ; lidongdong
 * @Description 会员收货地址表
 * @Date 2019-11-26
 */
@Data
@Entity
@Table(name = "ums_member_receive_address")
public class UmsMemberReceiveAddressEntity implements Serializable {
    private static final long serialVersionUID = 5439229334371247234L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "member_id", columnDefinition = "bigint(20) unsigned COMMENT '会员ID'", nullable = false)
    private Long memberId;

    @Column(name = "name", columnDefinition = "varchar(64) COMMENT '收货人名称'", nullable = false)
    private String name;

    @Column(name = "post_code", columnDefinition = "varchar(32) COMMENT '邮政编码'", nullable = false)
    private String postCode;

    @Column(name = "province", columnDefinition = "varchar(32) COMMENT '省份/直辖市'", nullable = false)
    private String province;

    @Column(name = "city", columnDefinition = "varchar(32) COMMENT '城市'", nullable = false)
    private String city;

    @Column(name = "district", columnDefinition = "varchar(32) COMMENT '区县'", nullable = false)
    private String region;

    @Column(name = "address", columnDefinition = "varchar(32) COMMENT '收货地址'", nullable = false)
    private String address;

    @Column(name = "mobile", columnDefinition = "char(15) COMMENT '联系电话'", nullable = false)
    private String mobile;

    @Column(name = "status", columnDefinition = "tinyint unsigned DEFAULT '1' COMMENT '是否为默认'", nullable = false)
    private Boolean defaultStatus;
}