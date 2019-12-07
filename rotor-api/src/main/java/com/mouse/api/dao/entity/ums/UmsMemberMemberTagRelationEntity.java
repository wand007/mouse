package com.mouse.api.dao.entity.ums;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author ; lidongdong
 * @Description 用户和标签关系表
 * @Date 2019-11-26
 */
@Data
@Entity
@Table(name = "ums_member_member_tag_relation")
public class UmsMemberMemberTagRelationEntity implements Serializable {
    private static final long serialVersionUID = 7960730367751482694L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "member_id", columnDefinition = "bigint(20) unsigned COMMENT '会员ID'", nullable = false)
    private Long memberId;

    @Column(name = "tag_id", columnDefinition = "bigint(20) unsigned COMMENT '标签ID'", nullable = false)
    private Long tagId;
}