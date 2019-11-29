package com.mouse.api.dao.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


/**
 * @author ; lidongdong
 * @Description 意见反馈
 * @Date 2019-11-26
 */
@Data
@Entity
@Table(name = "tbl_feed_back")
public class FeedbackEntity implements Serializable {

    private static final long serialVersionUID = -3649670306958086943L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "user_id", columnDefinition = "int COMMENT '会员ID'", nullable = false)
    private Integer userId;
    @Column(name = "user_name", columnDefinition = "varchar(32) COMMENT '会员名称'", nullable = false)
    private String userName;
    @Column(name = "merchant_id", columnDefinition = "int default '0' COMMENT '商户id'", nullable = false)
    private Integer merchant_id;
    @Column(name = "mobile", columnDefinition = "char(12) COMMENT '手机号码'", nullable = false)
    private String mobile;
    @Column(name = "feed_type", columnDefinition = "tinyint unsigned DEFAULT '1' COMMENT '反馈类型'", nullable = false)
    private Integer feedType;
    @Column(name = "status", columnDefinition = "tinyint unsigned DEFAULT '1' COMMENT '状态'", nullable = false)
    private Integer status;
    @Column(name = "content", columnDefinition = "text COMMENT '详细内容'", nullable = false)
    private String content;


    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @CreationTimestamp
    @Column(name = "create_date")
    public Date createDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @UpdateTimestamp
    @Column(name = "update_date")
    public Date updateDate;
}
