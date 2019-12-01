package com.mouse.api.dao.entity.sys;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import java.io.Serializable;

/**
 * @author ; lidongdong
 * @Description 短信配置信息
 * @Date 2019-11-26
 */
@Data
public class SysSmsConfigEntity implements Serializable {

    private static final long serialVersionUID = -4190172664146443174L;
    /**
     * 类型 1：创瑞
     */
    @Range(min = 1, max = 3, message = "类型错误")
    private Integer type;

    /**
     * 短信发送域名
     */
    private String domain;

    /**
     * 用户名
     */
    private String name;

    /**
     * 密码(md5加密)
     */
    private String pwd;

    /**
     * 签名
     */
    private String sign;

}
