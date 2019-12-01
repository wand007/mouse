package com.mouse.api.dao.entity.user;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;


/**
 * @author ; lidongdong
 * @Description 会员等级
 * @Date 2019-11-26
 */
@Data
@Entity
@Table(name = "tbl_user_level")
public class UserLevelEntity implements Serializable {

    private static final long serialVersionUID = -8591881919443311324L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name", columnDefinition = "varchar(32) COMMENT '名称'", nullable = false)
    private String name;
    @Column(name = "description", columnDefinition = "varchar(64) COMMENT '描述'", nullable = false)
    private String description;

}
