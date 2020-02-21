package com.mouse.api.hystrix;

import com.mouse.api.commons.req.SaveCommentReq;
import com.mouse.api.commons.req.SaveOrderReq;
import com.mouse.api.feign.OrderFeign;
import com.mouse.core.base.R;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * @author ; lidongdong
 * @Description 订单服务 熔断器
 * @Date 2019-11-30
 */
@Slf4j
@Component
public class HystrixOrderFeign implements FallbackFactory<OrderFeign> {
    @Override
    public OrderFeign create(Throwable throwable) {

        log.error("错误信息：", throwable);
        return new OrderFeign() {
            @Override
            public R findPage(Integer userId, Integer showType, Integer referer, @Min(value = 0, message = "必须从0页开始") Integer pageNum, @Min(value = 1, message = "每页必须大于1") @Max(value = 300, message = "每页必须小于300") Integer pageSize, String sort, String order) {
                return R.error();
            }

            @Override
            public R detail(Integer userId, String orderId) {
                return R.error();
            }

            @Override
            public R submit(Integer userId, SaveOrderReq param) {
                return R.error();
            }

            @Override
            public R cancel(Integer userId, String orderId) {
                return R.error();
            }

            @Override
            public R prepay(Integer userId, String body, HttpServletRequest request) {
                return R.error();
            }

            @Override
            public R h5pay(Integer userId, String body, HttpServletRequest request) {
                return R.error();
            }

            @Override
            public R payNotify(HttpServletRequest request, HttpServletResponse response) {
                return R.error();
            }

            @Override
            public R refund(Integer userId, String orderId) {
                return R.error();
            }

            @Override
            public R confirm(Integer userId, String orderId) {
                return R.error();
            }

            @Override
            public R delete(Integer userId, String orderId) {
                return R.error();
            }

            @Override
            public R goods(Integer userId, String orderId, Integer goodsId) {
                return R.error();
            }

            @Override
            public R comment(Integer userId, SaveCommentReq param) {
                return R.error();
            }
        };
    }
}
