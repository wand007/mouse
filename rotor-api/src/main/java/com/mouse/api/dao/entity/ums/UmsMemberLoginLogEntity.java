package com.mouse.api.dao.entity.ums;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author ; lidongdong
 * @Description 会员登录记录
 * @Date 2019-11-26
 */
@Data
@Entity
@Table(name = "ums_member_login_log")
public class UmsMemberLoginLogEntity implements Serializable {
    private static final long serialVersionUID = 843633304431463806L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "member_id", columnDefinition = "bigint(20) unsigned COMMENT '会员ID'", nullable = false)
    private Long memberId;

    @Column(name = "ip", columnDefinition = "varchar(64) COMMENT 'ip'", nullable = false)
    private String ip;

    @Column(name = "city", columnDefinition = "varchar(32) COMMENT '城市'", nullable = false)
    private String city;

    @Column(name = "login_type", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '登录类型：0->PC；1->android;2->ios;3->小程序'", nullable = false)
    private Integer loginType;

    @Column(name = "province", columnDefinition = "varchar(32) COMMENT '省'", nullable = false)
    private String province;

}