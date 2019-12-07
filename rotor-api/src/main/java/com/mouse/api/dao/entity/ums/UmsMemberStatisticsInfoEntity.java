package com.mouse.api.dao.entity.ums;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author ; lidongdong
 * @Description 会员统计信息
 * @Date 2019-11-26
 */
@Data
@Entity
@Table(name = "ums_member_statistics_info")
public class UmsMemberStatisticsInfoEntity implements Serializable {
    private static final long serialVersionUID = 6651685226772634889L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "member_id", columnDefinition = "bigint(20) unsigned COMMENT '会员ID'", nullable = false)
    private Long memberId;

    @Column(name = "consume_amount", columnDefinition = "decimal(10,2) default '0' COMMENT '累计消费金额'", nullable = false)
    private BigDecimal consumeAmount;

    @Column(name = "order_count", columnDefinition = "int unsigned default '0' COMMENT '订单数量'", nullable = false)
    private Integer orderCount;

    @Column(name = "coupon_count", columnDefinition = "int unsigned default '0' COMMENT '优惠券数量'", nullable = false)
    private Integer couponCount;

    @Column(name = "comment_count", columnDefinition = "int unsigned default '0' COMMENT '评价数'", nullable = false)
    private Integer commentCount;

    @Column(name = "return_order_count", columnDefinition = "int unsigned default '0' COMMENT '退货数量'", nullable = false)
    private Integer returnOrderCount;

    @Column(name = "login_count", columnDefinition = "int unsigned default '0' COMMENT '登录次数'", nullable = false)
    private Integer loginCount;

    @Column(name = "attend_count", columnDefinition = "int unsigned default '0' COMMENT '关注数量'", nullable = false)
    private Integer attendCount;

    @Column(name = "fans_count", columnDefinition = "int unsigned default '0' COMMENT '粉丝数量'", nullable = false)
    private Integer fansCount;

    @Column(name = "collect_product_count", columnDefinition = "int unsigned default '0' COMMENT '收藏商品数量'", nullable = false)
    private Integer collectProductCount;

    @Column(name = "collect_subject_count", columnDefinition = "int unsigned default '0' COMMENT '收藏专题数量'", nullable = false)
    private Integer collectSubjectCount;

    @Column(name = "collect_topic_count", columnDefinition = "int unsigned default '0' COMMENT '收藏话题数量'", nullable = false)
    private Integer collectTopicCount;

    @Column(name = "collect_comment_count", columnDefinition = "int unsigned default '0' COMMENT '收藏评价数量'", nullable = false)
    private Integer collectCommentCount;

    @Column(name = "invite_friend_count", columnDefinition = "int unsigned default '0' COMMENT '邀请好友数量'", nullable = false)
    private Integer inviteFriendCount;

    @Column(name = "recent_order_time", columnDefinition = "datetime COMMENT '最后一次下订单时间'")
    private Date recentOrderTime;

}