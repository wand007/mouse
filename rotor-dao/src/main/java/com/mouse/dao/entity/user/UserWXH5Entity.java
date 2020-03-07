package com.mouse.dao.entity.user;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;


/**
 * @author ; lidongdong
 * @Description 用户微信信息表
 * @Date 2019-12-11
 */
@Data
@Entity
@Table(name = "tbl_user_wx_h5")
@org.hibernate.annotations.Table(appliesTo = "tbl_user_wx_h5", comment = "用户微信H5信息表")
public class UserWXH5Entity implements Serializable {
    private static final long serialVersionUID = 3618527625877209564L;
    @Id
    @Column(name = "user_id", columnDefinition = "varchar(32) COMMENT '用户ID'", nullable = false)
    private String userId;

    @Column(name = "open_id", columnDefinition = "varchar(64) COMMENT '用户在共享平台的唯一标识'")
    private String openId;

    @Column(name = "union_id", columnDefinition = "varchar(64) COMMENT '用户在 公众号的唯一标识'")
    private String unionId;

    @Column(name = "mobile", columnDefinition = "varchar(20) COMMENT '用户手机号码'")
    private String mobile;

    @Column(name = "nick_name", columnDefinition = "varchar(32)  COMMENT '用户昵称或网络名称'")
    private String nickName;

    @Column(name = "avatar_url", columnDefinition = "varchar(255) COMMENT '用户头像图片'")
    private String avatarUrl;

    @Column(name = "sex", columnDefinition = "varchar(4) DEFAULT '0' COMMENT '用户的性别，值为1时是男性，值为2时是女性，值为0时是未知'")
    private String sex;

    @Column(name = "province", columnDefinition = "varchar(255) COMMENT '用户个人资料填写的省份'")
    private String province;

    @Column(name = "city", columnDefinition = "varchar(255) COMMENT '普通用户个人资料填写的城市'")
    private String city;

    @Column(name = "country", columnDefinition = "varchar(255) COMMENT '国家，如中国为CN'")
    private String country;

    @CreationTimestamp
    @Column(name = "create_time", columnDefinition = "datetime  COMMENT '创建时间'", nullable = false)
    private Date createTime;

    @UpdateTimestamp
    @Column(name = "update_time", columnDefinition = "datetime COMMENT '更新时间'", nullable = false)
    private Date updateTime;

    @Column(name = "deleted", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '逻辑删除 0 未删除，1 删除'")
    private Boolean deleted;
}