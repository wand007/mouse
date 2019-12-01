package com.mouse.api.dao.entity.resource;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


/**
 * @author ; lidongdong
 * @Description 商品咨询
 * @Date 2019-11-26
 */
@Data
@Entity
@Table(name = "tbl_goods_issue")
public class GoodsIssueEntity implements Serializable {

    private static final long serialVersionUID = -3066257834955736682L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "question", columnDefinition = "varchar(255) COMMENT '问题'", nullable = false)
    private String question;
    @Column(name = "answer", columnDefinition = "varchar(255) COMMENT '回答'", nullable = false)
    private String answer;
    @Column(name = "merchant_id", columnDefinition = "varchar(32) COMMENT '供应商ID'", nullable = false)
    private String merchantId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @CreationTimestamp
    @Column(name = "create_date")
    public Date createDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @UpdateTimestamp
    @Column(name = "update_date")
    public Date updateDate;


}
