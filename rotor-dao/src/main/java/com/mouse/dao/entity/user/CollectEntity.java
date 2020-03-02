package com.mouse.dao.entity.user;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author ; lidongdong
 * @Description 收藏表
 * @Date 2019-11-26
 */
@Data
@Entity
@Table(name = "tbl_collect")
public class CollectEntity implements Serializable {
    private static final long serialVersionUID = -5125698236326494246L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_id", columnDefinition = "varchar(32) COMMENT '用户ID'", nullable = false)
    private String userId;

    @Column(name = "value_id", columnDefinition = "int unsigned DEFAULT '0' COMMENT '如果type=0，则是商品ID；如果type=1，则是专题ID'", nullable = false)
    private Integer valueId;

    @Column(name = "type", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '收藏类型，如果type=0，则是商品ID；如果type=1，则是专题ID'", nullable = false)
    private Integer type;

    @CreationTimestamp
    @Column(name = "add_time", columnDefinition = "datetime  COMMENT '创建时间'", nullable = false)
    private LocalDateTime addTime;

    @UpdateTimestamp
    @Column(name = "update_time", columnDefinition = "datetime COMMENT '更新时间'", nullable = false)
    private LocalDateTime updateTime;

   @Column(name = "deleted", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '逻辑删除 0 未删除，1 删除'", nullable = false)
    private Boolean deleted;
}