package com.mouse.dao.entity.user;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author ; lidongdong
 * @Description 用户通知模版formid表
 * @Date 2019-11-26
 */
@Data
@Entity
@Table(name = "tbl_user_formid")
public class UserFormIdEntity implements Serializable {
    private static final long serialVersionUID = -1772996238684924859L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "formId", columnDefinition = "varchar(32) COMMENT '缓存的FormId'", nullable = false)
    private String formId;

    @Column(name = "is_prepay", columnDefinition = "tinyint unsigned DEFAULT '1' COMMENT '是FormId还是prepayId'", nullable = false)
    private Boolean isPrepay;

    @Column(name = "useAmount", columnDefinition = "int unsigned DEFAULT '0' COMMENT '可用次数，fromId为1，prepay为3，用1次减1'", nullable = false)
    private Integer useAmount;

    @Column(name = "expire_time", columnDefinition = "datetime  COMMENT '过期时间，腾讯规定为7天'", nullable = false)
    private LocalDateTime expireTime;

    @Column(name = "openId", columnDefinition = "varchar(32) COMMENT '微信登录openid'", nullable = false)
    private String openId;

    @CreationTimestamp
    @Column(name = "add_time", columnDefinition = "datetime  COMMENT '创建时间'", nullable = false)
    private LocalDateTime addTime;

    @UpdateTimestamp
    @Column(name = "update_time", columnDefinition = "datetime COMMENT '更新时间'", nullable = false)
    private LocalDateTime updateTime;

   @Column(name = "deleted", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '逻辑删除 0 未删除，1 删除'", nullable = false)
    private Boolean deleted;
}