package com.mouse.api.commons.req;

import lombok.Data;

import java.io.Serializable;

/**
 * @author ; lidongdong
 * @Description 收货地址表
 * @Date 2019-11-26
 */
@Data
public class SaveAddressReq implements Serializable {
    private static final long serialVersionUID = -6509844688502599822L;
    private Integer id;

    /**
     * 收货人名称
     */
    private String name;

    /**
     * 用户表的用户ID
     */
    private Integer userId;

    /**
     * 省
     */
    private String province;

    /**
     * 城市
     */
    private String city;

    /**
     * 县/区
     */
    private String county;

    /**
     * 详细收货地址
     */
    private String address;

    /**
     * 地区编码
     */
    private String areaCode;

    /**
     * 邮政编码
     */
    private String postalCode;

    /**
     * 手机号码
     */
    private String tel;

    /**
     * 是否默认地址
     */
    private Boolean isDefault;
}