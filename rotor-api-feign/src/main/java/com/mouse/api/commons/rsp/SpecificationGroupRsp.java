package com.mouse.api.commons.rsp;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author ; lidongdong
 * @Description
 * @Date 2020-03-08
 */
@Data
public class SpecificationGroupRsp implements Serializable {
    private static final long serialVersionUID = 198350574261879358L;
    private String name;
    private List<GoodsSpecificationRsp> valueList;
}
