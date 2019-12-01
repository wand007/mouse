package com.mouse.api.dao.entity.operate;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


/**
 * @author ; lidongdong
 * @Description
 * @Date 2019-11-26
 */
@Data
@Entity
@Table(name = "tbl_ad_position")
public class AdPositionEntity implements Serializable {

    private static final long serialVersionUID = 8372459507093619756L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "\"name\"", columnDefinition = "varchar(60) COMMENT '位置名称'", nullable = false)
    private String name;
    @Column(name = "width", columnDefinition = "smallint unsigned DEFAULT '0' COMMENT '宽度'", nullable = false)
    private Integer width;
    @Column(name = "height", columnDefinition = "smallint unsigned DEFAULT '0' COMMENT '高度'", nullable = false)
    private Integer height;
    @Column(name = "\"remark\"", columnDefinition = "varchar(32) COMMENT '备注'")
    private String remark;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @CreationTimestamp
    @Column(name = "create_date")
    public Date createDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @UpdateTimestamp
    @Column(name = "update_date")
    public Date updateDate;
}
