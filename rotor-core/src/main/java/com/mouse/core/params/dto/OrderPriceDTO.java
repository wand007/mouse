package com.mouse.core.params.dto;

import com.mouse.core.base.R;
import lombok.Data;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author ; lidongdong
 * @Description
 * @Date 2020-04-13
 */
@Data
public class OrderPriceDTO {
    // 团购优惠
    private BigDecimal grouponPrice = BigDecimal.ZERO;

    // 选中商品金额
    private BigDecimal checkedGoodsPrice = BigDecimal.ZERO;

    // 使用优惠券减免的金额
    private BigDecimal couponPrice = BigDecimal.ZERO;

    // 根据订单商品总价计算运费，满足条件（例如88元）则免运费，否则需要支付运费（例如8元）；
    private BigDecimal freightPrice = BigDecimal.ZERO;

    // 可以使用的其他钱，例如用户积分
    private BigDecimal integralPrice = BigDecimal.ZERO;

    // 订单费用
    private BigDecimal orderTotalPrice = BigDecimal.ZERO;

    // 最终支付费用
    private BigDecimal actualPrice = BigDecimal.ZERO;

}
