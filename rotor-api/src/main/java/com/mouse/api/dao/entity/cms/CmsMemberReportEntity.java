package com.mouse.api.dao.entity.cms;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author ; lidongdong
 * @Description 用户举报表
 * @Date 2019-11-26
 */
@Data
@Entity
@Table(name = "cms_help_category")
public class CmsMemberReportEntity implements Serializable {
    private static final long serialVersionUID = 6367898063080566093L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "report_type", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '举报类型：0->商品评价；1->话题内容；2->用户评论'", nullable = false)
    private Integer reportType;

    @Column(name = "report_member_name", columnDefinition = "varchar(64) COMMENT '举报人'", nullable = false)
    private String reportMemberName;

    @Column(name = "report_object", columnDefinition = "varchar(64) COMMENT '举报内容'", nullable = false)
    private String reportObject;

    @Column(name = "report_status", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '举报状态：0->未处理；1->已处理'", nullable = false)
    private Integer reportStatus;

    @Column(name = "handle_status", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '处理结果：0->无效；1->有效；2->恶意'", nullable = false)
    private Integer handleStatus;

    @Column(name = "note", columnDefinition = "varchar(64) COMMENT '备注'", nullable = false)
    private String note;

}