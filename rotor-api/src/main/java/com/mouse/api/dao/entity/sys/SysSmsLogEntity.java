package com.mouse.api.dao.entity.sys;

import com.fasterxml.jackson.annotation.JsonFormat;
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
 * @Description 发送短信日志
 * @Date 2019-11-26
 */
@Data
@Entity
@Table(name = "tbl_sys_role_menu")
public class SysSmsLogEntity implements Serializable {

    private static final long serialVersionUID = 5793028238291201351L;
    @Id
    @Column(name = "id", columnDefinition = "varchar(60) default '0' COMMENT '发送短信日志ID'", nullable = false)
    private String id;
    @Column(name = "user_id", columnDefinition = "varchar(32) COMMENT '操作人ID'", nullable = false)
    private String userId;
    @Column(name = "content", columnDefinition = "text COMMENT '必填参数。发送内容（1-500 个汉字）UTF-8编码'", nullable = false)
    private String content;

    @Column(name = "mobile", columnDefinition = "text COMMENT '手机号码。多个以英文逗号隔开'", nullable = false)
    private String mobile;
    @Column(name = "stime", columnDefinition = "varchar(32) COMMENT '可选参数。发送时间，填写时已填写的时间发送，不填时为当前时间发送'", nullable = false)
    private Date stime;
    @Column(name = "sign", columnDefinition = "varchar(32) COMMENT '必填参数。用户签名'", nullable = false)
    private String sign;
    @Column(name = "type", columnDefinition = "varchar(32) COMMENT '必填参数。固定值 pt'", nullable = false)
    private String type;
    @Column(name = "extno", columnDefinition = "varchar(32) COMMENT ' 可选参数。扩展码，用户定义扩展码，只能为数字'", nullable = false)
    private String extno;
    @Column(name = "send_status", columnDefinition = "tinyint unsigned DEFAULT '1' COMMENT '发送渠道 1成功 0失败'", nullable = false)
    private Integer sendStatus;
    @Column(name = "send_id", columnDefinition = "varchar(32) COMMENT '发送编号'", nullable = false)
    private String sendId;
    @Column(name = "invalid_num", columnDefinition = "int unsigned DEFAULT '0' COMMENT '无效号码数'", nullable = false)
    private Integer invalidNum;
    @Column(name = "success_num", columnDefinition = "int unsigned DEFAULT '0' COMMENT '成功提交数'", nullable = false)
    private Integer successNum;
    @Column(name = "black_num", columnDefinition = "int unsigned DEFAULT '0' COMMENT '黑名单数'", nullable = false)
    private Integer blackNum;
    @Column(name = "return_msg", columnDefinition = "varchar(64) COMMENT '返回消息'", nullable = false)
    private String returnMsg;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @CreationTimestamp
    @Column(name = "create_date")
    public Date createDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @UpdateTimestamp
    @Column(name = "update_date")
    public Date updateDate;
}
