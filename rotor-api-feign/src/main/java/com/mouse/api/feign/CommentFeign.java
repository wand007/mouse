package com.mouse.api.feign;

import com.mouse.api.commons.req.SaveCommentReq;
import com.mouse.api.hystrix.HystrixCommentFeign;
import com.mouse.core.base.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * @author ; lidongdong
 * @Description 用户评论服务 feign
 * @Date 2020-01-12
 */
@FeignClient(name = "http://ROTOR-API",
        path = "rotor-api/comment",
        fallbackFactory = HystrixCommentFeign.class)
public interface CommentFeign {


    /**
     * 发表评论
     *
     * @param userId 用户ID
     * @param param  评论内容
     * @return 发表评论操作结果
     */
    @PostMapping("post")
    R post(@RequestParam(name = "userId") Integer userId,
           @RequestBody SaveCommentReq param);

    /**
     * 评论数量
     *
     * @param type    类型ID。 如果是0，则查询商品评论；如果是1，则查询专题评论。
     * @param valueId 商品或专题ID。如果type是0，则是商品ID；如果type是1，则是专题ID。
     * @return 评论数量
     */
    @GetMapping("count")
    R count(@RequestParam(name = "type") Integer type,
            @RequestParam(name = "valueId") Integer valueId);

    /**
     * 评论列表
     *
     * @param type     类型ID。 如果是0，则查询商品评论；如果是1，则查询专题评论。
     * @param valueId  商品或专题ID。如果type是0，则是商品ID；如果type是1，则是专题ID。
     * @param showType 显示类型。如果是0，则查询全部；如果是1，则查询有图片的评论。
     * @param pageNum  分页页数
     * @param pageSize 分页大小
     * @return 评论列表
     */
    @GetMapping("findPage")
    R findPage(@RequestParam(name = "valueId") Integer valueId,
               @RequestParam(name = "type") Integer type,
               @RequestParam(name = "showType") Integer showType,
               @Min(value = 0, message = "必须从0页开始")
               @RequestParam(name = "pageNum", defaultValue = "0", required = false) Integer pageNum,
               @Min(value = 1, message = "每页必须大于1")
               @Max(value = 300, message = "每页必须小于300")
               @RequestParam(name = "pageSize", defaultValue = "20", required = false) Integer pageSize);

}
