package com.mouse.api.dao.entity.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


/**
 * @author ; lidongdong
 * @Description 客户实体
 * @Date 2019-11-26
 */
@Data
@Entity
@Table(name = "tbl_customer")
public class CustomerEntity implements Serializable {

    private static final long serialVersionUID = 6290678158163388688L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name", columnDefinition = "varchar(60) COMMENT '会员姓名'", nullable = false)
    private String uname;
    @Column(name = "real_name", columnDefinition = "varchar(32) COMMENT '真实姓名'", nullable = false)
    private String realName;
    @Column(name = "gender", columnDefinition = "tinyint unsigned DEFAULT '1' COMMENT '客户性别 0:未知性别 1：男 2：女'", nullable = false)
    private Integer gender;
    @Column(name = "birthday", columnDefinition = "datetime COMMENT '出生日期'")
    private Date birthday;
    @Column(name = "mobile", columnDefinition = "char(15) COMMENT '手机号码'", nullable = false)
    private String mobile;
    @Column(name = "upkeep_state", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '维护状态 0：未维护 1:已维护'", nullable = false)
    private Integer upkeepState;
    @Column(name = "customer_state", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '客户属性 0:默认用户 1：准客户'", nullable = false)
    private Integer customerState;
    @Column(name = "remarks", columnDefinition = "varchar(60) COMMENT '备注'", nullable = false)
    private String remarks;
    @Column(name = "uid", columnDefinition = "varchar(32) COMMENT '创建人id'", nullable = false)
    private String uid;
    @Column(name = "job", columnDefinition = "varchar(60) COMMENT '工作'", nullable = false)
    private String job;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @CreationTimestamp
    @Column(name = "create_date")
    public Date createDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @UpdateTimestamp
    @Column(name = "update_date")
    public Date updateDate;
}
