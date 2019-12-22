package com.mouse.api.commons.rsp;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class GrouponRuleRsp implements Serializable {
    private static final long serialVersionUID = -4600972108970956727L;
    private Integer id;
    private String name;
    private String brief;
    private String picUrl;
    private BigDecimal counterPrice;
    private BigDecimal retailPrice;
    private BigDecimal grouponPrice;
    private BigDecimal grouponDiscount;
    private Integer grouponMember;
    private LocalDateTime expireTime;

}
