package com.mouse.api.commons.req;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author ; lidongdong
 * @Description 意见反馈表
 * @Date 2019-11-26
 */
@Data
public class FeedbackReq implements Serializable {

    private static final long serialVersionUID = -6842009786194420282L;
    /**
     * 用户表的用户ID
     */
    private String userId;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 反馈类型
     */
    private String feedType;

    /**
     * 反馈内容
     */
    private String content;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 是否含有图片
     */
    private Boolean hasPicture;

    /**
     * 图片地址列表，采用JSON数组格式
     */
    private List<String> picUrls;

}