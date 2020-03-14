package com.mouse.api.commons.req;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author ; lidongdong
 * @Description 购物车选中记录入参参数
 * @Date 2020-03-15
 */
@Data
public class CartCheckedReq implements Serializable {
    /**
     * 选中状态
     */
    private Boolean isChecked;
    /**
     * 产品ID集合
     */
    private List<Integer> productIds;
}
