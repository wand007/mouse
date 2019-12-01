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
 * @Description 会员实体
 * @Date 2019-11-26
 */
@Data
@Entity
@Table(name = "tbl_user")
public class UserEntity implements Serializable {

    private static final long serialVersionUID = -6624897431782525727L;
    @Id
    @Column(name = "id", columnDefinition = "varchar(32) COMMENT '会员ID'", nullable = false)
    private Integer id;
    @Column(name = "user_name", columnDefinition = "varchar(32) COMMENT '会员名称'", nullable = false)
    private String username;
    @Column(name = "nick_name", columnDefinition = "varchar(32) COMMENT '会员昵称'", nullable = false)
    private String nickname;
    @Column(name = "real_name", columnDefinition = "varchar(32) COMMENT '会员真实姓名'", nullable = false)
    private String realName;
    @Column(name = "password", columnDefinition = "varchar(32) COMMENT '会员密码'", nullable = false)
    private String password;
    @Column(name = "gender", columnDefinition = "tinyint unsigned DEFAULT '1' COMMENT '客户性别 0:未知性别 1：男 2：女'", nullable = false)
    private Integer gender;
    @Column(name = "birthday", columnDefinition = "datetime COMMENT '出生日期'")
    private Date birthday;
    @Column(name = "mobile", columnDefinition = "char(15) COMMENT '手机号码'", nullable = false)
    private String mobile;
    @Column(name = "last_login_time", columnDefinition = "datetime COMMENT '最后登录时间'")
    private Date lastLoginTime;
    @Column(name = "last_login_ip", columnDefinition = "varchar(32) COMMENT '最后登录Ip'", nullable = false)
    private String lastLoginIp;
    @Column(name = "user_level_id", columnDefinition = "int unsigned default '0' COMMENT '会员等级'", nullable = false)
    private Integer userLevelId;

    @Column(name = "register_ip", columnDefinition = "varchar(32) COMMENT '注册Ip'", nullable = false)
    private String registerIp;
    @Column(name = "avatar", columnDefinition = "varchar(32) COMMENT '头像'", nullable = false)
    private String avatar;
    @Column(name = "weixin_open_id", columnDefinition = "varchar(32) COMMENT '微信Id'", nullable = false)
    private String weixinOpenId;
    @Column(name = "id_card", columnDefinition = "varchar(32) COMMENT '身份证号'", nullable = false)
    private String idCard;
    @Column(name = "promoter_id", columnDefinition = "varchar(32) COMMENT '推广人id'", nullable = false)
    private String promoterId;
    @Column(name = "promoter_name", columnDefinition = "varchar(32) COMMENT '推广人姓名'", nullable = false)
    private String promoterName;

    @Column(name = "qr_code", columnDefinition = "varchar(64) COMMENT '二维码路径'", nullable = false)
    private String qrCode;
    @Column(name = "is_real", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '是否实名认证 0：否 1：是'", nullable = false)
    private Boolean isReal;
    @Column(name = "is_return_cash", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '是否推荐购买返现 0没有、1已返现'", nullable = false)
    private Boolean isReturnCash;
    @Column(name = "first_buy_money", columnDefinition = "decimal(10,2) default '0' COMMENT '首次购买金额'", nullable = false)
    private BigDecimal firstBuyMoney;

    @Column(name = "total_brokerage", columnDefinition = "decimal(10,2) default '0' COMMENT '总佣金'", nullable = false)
    private BigDecimal totalBrokerage;
    @Column(name = "valid_brokerage", columnDefinition = "decimal(10,2) default '0' COMMENT '可提现金额'", nullable = false)
    private BigDecimal validBrokerage;
    @Column(name = "withdraw_cash", columnDefinition = "decimal(10,2) default '0' COMMENT '已提现金额'", nullable = false)
    private BigDecimal withdrawCash;


    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @CreationTimestamp
    @Column(name = "create_date")
    public Date createDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @UpdateTimestamp
    @Column(name = "update_date")
    public Date updateDate;
}
