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
 * @Description 分销用户
 * @Date 2019-11-26
 */
@Data
@Entity
@Table(name = "tbl_user_mls")
public class UserMlsEntity implements Serializable {
    private static final long serialVersionUID = -5991214940308427999L;
    /**
     * 分销用户ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer mlsUserId;
    @Column(name = "user_name", columnDefinition = "varchar(32) COMMENT '用户名'", nullable = false)
    private String userName;
    @Column(name = "nick_name", columnDefinition = "varchar(32) COMMENT '昵称'", nullable = false)
    private String nickname;
    @Column(name = "login_name", columnDefinition = "varchar(32) COMMENT '登录名手机号+设备号'", nullable = false)
    private String loginName;
    @Column(name = "login_password", columnDefinition = "varchar(33) COMMENT '登录密码-用户Id'", nullable = false)
    private String loginPassword;
    @Column(name = "device_id", columnDefinition = "varchar(32) COMMENT '设备编号'", nullable = false)
    private String deviceId;
    @Column(name = "user_tel", columnDefinition = "char(15) COMMENT '电话'", nullable = false)
    private String userTel;
    @Column(name = "profile_photo", columnDefinition = "varchar(64) COMMENT '头像'", nullable = false)
    private String profilePhoto;
    @Column(name = "all_sales", columnDefinition = "int unsigned default '0' COMMENT '总销售额-单位分'", nullable = false)
    private Integer allSales;
    @Column(name = "today_sales", columnDefinition = "int unsigned default '0' COMMENT '今天销售额-单位分'", nullable = false)
    private Integer todaySales;
    @Column(name = "all_profit", columnDefinition = "int unsigned default '0' COMMENT '总分润-单位分'", nullable = false)
    private Integer allProfit;
    @Column(name = "real_profit", columnDefinition = "int unsigned default '0' COMMENT '可提现分润-单位分'", nullable = false)
    private Integer realProfit;
    @Column(name = "father_user_id", columnDefinition = "int unsigned default '0' COMMENT '父用户ID'", nullable = false)
    private Integer fatherUserId;
    @Column(name = "son_user_sum", columnDefinition = "int unsigned default '0' COMMENT '子用户数量'", nullable = false)
    private Integer sonUserSum;
    @Column(name = "fx1", columnDefinition = "decimal(10,2) default '0' COMMENT '一级分佣'", nullable = false)
    private BigDecimal fx1;
    @Column(name = "fx2", columnDefinition = "decimal(10,2) default '0' COMMENT '二级分佣'", nullable = false)
    private BigDecimal fx2;
    @Column(name = "fx", columnDefinition = "decimal(10,2) default '0' COMMENT '本人分佣'", nullable = false)
    private BigDecimal fx;
    @Column(name = "pfx", columnDefinition = "decimal(10,2) default '0' COMMENT '平台分佣'", nullable = false)
    private BigDecimal pfx;
    @Column(name = "merchant_id", columnDefinition = "int unsigned default '0' COMMENT '商户id'", nullable = false)
    private BigDecimal merchantId;
    @Column(name = "fid", columnDefinition = "int unsigned default '0' COMMENT '父ID'", nullable = false)
    private Integer fid;
    @Column(name = "root_id", columnDefinition = "int unsigned default '0' COMMENT '分销用户根节点'", nullable = false)
    private Integer rootId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @CreationTimestamp
    @Column(name = "create_date")
    public Date createDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @UpdateTimestamp
    @Column(name = "update_date")
    public Date updateDate;


}