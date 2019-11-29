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
 * @Description
 * @Date 2019-11-26
 */
@Data
@Entity
@Table(name = "tbl_search_history")
public class SearchHistoryEntity implements Serializable {

    private static final long serialVersionUID = -5151298463811628922L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "user_id", columnDefinition = "int COMMENT '会员ID'", nullable = false)
    private Integer userId;
    @Column(name = "keyword", columnDefinition = "varchar(32) COMMENT '关键字'", nullable = false)
    private String keyword;
    @Column(name = "referer", columnDefinition = "tinyint unsigned DEFAULT '1' COMMENT '搜索来源，如PC、小程序、APP等'", nullable = false)
    private Integer referer;

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
