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
 * @Description 用户收藏记录
 * @Date 2019-11-26
 */
@Data
@Entity
@Table(name = "tbl_user_collection")
public class UserCollectionEntity implements Serializable {

    private static final long serialVersionUID = -2343005670826308128L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "user_id", columnDefinition = "varchar(32) COMMENT '会员ID'", nullable = false)
    private String userId;
    @Column(name = "goods_id", columnDefinition = "varchar(32) COMMENT '商品id'", nullable = false)
    private Integer goodsId;
    @Column(name = "is_attention", columnDefinition = "tinyint unsigned DEFAULT '1' COMMENT '是否提醒'", nullable = false)
    private Boolean isAttention;
    @Column(name = "type_id", columnDefinition = "int unsigned default '0' COMMENT '类型ID'", nullable = false)
    private Integer typeId;

    //出参时间格式化
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @CreationTimestamp
    @Column(name = "create_date")
    public Date createDate;
    //出参时间格式化
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @UpdateTimestamp
    @Column(name = "update_date")
    public Date updateDate;
}
