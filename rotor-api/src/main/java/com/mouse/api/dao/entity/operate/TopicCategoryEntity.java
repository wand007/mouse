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
 * @Description 活动类别
 * @Date 2019-11-26
 */
@Data
@Entity
@Table(name = "tbl_topic_category")
public class TopicCategoryEntity implements Serializable {

    private static final long serialVersionUID = 5435713248121579245L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "title", columnDefinition = "varchar(60) COMMENT '活动类别主题'", nullable = false)
    private String title;
    @Column(name = "pic_url", columnDefinition = "varchar(255) COMMENT '活动类别图片链接'", nullable = false)
    private String picUrl;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @CreationTimestamp
    @Column(name = "create_date")
    public Date createDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @UpdateTimestamp
    @Column(name = "update_date")
    public Date updateDate;
}
