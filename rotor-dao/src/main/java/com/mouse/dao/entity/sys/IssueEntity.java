package com.mouse.dao.entity.sys;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author ; lidongdong
 * @Description 常见问题表
 * @Date 2019-11-26
 */
@Data
@Entity
@Table(name = "tbl_issue")
public class IssueEntity implements Serializable {
    private static final long serialVersionUID = 2087207168485276046L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "question", columnDefinition = "varchar(255) COMMENT '问题标题'", nullable = false)
    private String question;

    @Column(name = "answer", columnDefinition = "varchar(255) COMMENT '问题答案'", nullable = false)
    private String answer;

    @CreationTimestamp
    @Column(name = "add_time", columnDefinition = "datetime  COMMENT '创建时间'", nullable = false)
    private LocalDateTime addTime;

    @UpdateTimestamp
    @Column(name = "update_time", columnDefinition = "datetime COMMENT '更新时间'", nullable = false)
    private LocalDateTime updateTime;

   @Column(name = "deleted", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '逻辑删除 0 未删除，1 删除'", nullable = false)
    private Boolean deleted;
}