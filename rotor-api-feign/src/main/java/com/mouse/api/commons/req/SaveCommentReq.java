package com.mouse.api.commons.req;

import lombok.Data;

import java.io.Serializable;

/**
 * @author ; lidongdong
 * @Description 评论表
 * @Date 2019-11-26
 */
@Data
public class SaveCommentReq implements Serializable {

    private static final long serialVersionUID = 3305784533790310064L;
    /**
     * 如果type=0，则是商品评论；如果是type=1，则是专题评论
     */
    private Integer valueId;

    /**
     * 评论类型，如果type=0，则是商品评论；如果是type=1，则是专题评论；如果type=3，则是订单商品评论
     */
    private Integer type;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 用户表的用户ID
     */
    private Integer userId;

    /**
     * 是否含有图片
     */
    private Boolean hasPicture;

    /**
     * 图片地址列表，采用JSON数组格式
     */
    private String picUrls;

    /**
     * 评分， 1-5
     */
    private Short star;

}