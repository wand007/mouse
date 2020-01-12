package com.mouse.api.feign;

import com.mouse.api.hystrix.HystrixCollectFeign;
import com.mouse.core.base.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * @author ; lidongdong
 * @Description 用户收藏服务 feign
 * @Date 2020-01-12
 */
@FeignClient(name = "http://ROTOR-API",
        path = "rotor-api/collect",
        fallbackFactory = HystrixCollectFeign.class)
public interface CollectFeign {


    /**
     * 用户收藏列表
     *
     * @param userId   用户ID
     * @param type     类型，如果是0则是商品收藏，如果是1则是专题收藏
     * @param pageNum  分页页数
     * @param pageSize 分页大小
     * @return
     */
    @GetMapping("findPage")
    R findPage(@RequestParam(name = "userId") Integer userId,
               @RequestParam(name = "type") Byte type,
               @Min(value = 0, message = "必须从0页开始")
               @RequestParam(name = "pageNum", defaultValue = "0", required = false) Integer pageNum,
               @Min(value = 1, message = "每页必须大于1")
               @Max(value = 300, message = "每页必须小于300")
               @RequestParam(name = "pageSize", defaultValue = "20", required = false) Integer pageSize);

    /**
     * 用户收藏添加或删除
     * <p>
     * 如果商品没有收藏，则添加收藏；如果商品已经收藏，则删除收藏状态。
     *
     * @param userId  用户ID
     * @param type    收藏类型，如果type=0，则是商品ID；如果type=1，则是专题ID
     * @param valueId 如果type=0，则是商品ID；如果type=1，则是专题ID
     * @return 操作结果
     */
    @PostMapping("addordelete")
    R addordelete(@RequestParam(name = "userId") Integer userId,
                  @RequestParam(name = "type") Integer type,
                  @RequestParam(name = "valueId") Integer valueId);
}
