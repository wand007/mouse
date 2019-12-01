package com.mouse.api.dao.entity.operate;

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
 * @Description 拼团详情
 * @Date 2019-11-26
 */
@Data
@Entity
@Table(name = "tbl_group_buying_detailed")
public class GroupBuyingDetailedEntity implements Serializable {

    private static final long serialVersionUID = -1486341180420331066L;
    @Id
    @Column(name = "id", columnDefinition = "varchar(32) COMMENT '拼团ID'", nullable = false)
    private String id;
    @Column(name = "group_buying_id", columnDefinition = "varchar(32) COMMENT '团购主表ID'", nullable = false)
    private String groupBuyingId;
    @Column(name = "user_id", columnDefinition = "varchar(32) COMMENT '会员ID'", nullable = false)
    private String userId;
    @Column(name = "user_name", columnDefinition = "varchar(32) COMMENT '会员名称'", nullable = false)
    private String userName;
    @Column(name = "user_img", columnDefinition = "varchar(64) COMMENT '用户图像'", nullable = false)
    private String userImg;
    @Column(name = "pay_time", columnDefinition = "datetime COMMENT '购买时间'")
    private Date payTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @CreationTimestamp
    @Column(name = "create_date")
    public Date createDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @UpdateTimestamp
    @Column(name = "update_date")
    public Date updateDate;
}
