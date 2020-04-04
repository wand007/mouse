package com.mouse.api.client.mall;

import com.mouse.api.base.GlobalExceptionHandler;
import com.mouse.api.commons.req.SaveCommentReq;
import com.mouse.api.feign.mall.CommentFeign;
import com.mouse.api.service.CommentService;
import com.mouse.api.service.UserService;
import com.mouse.core.base.R;
import com.mouse.core.enums.CommentTypeEnum;
import com.mouse.core.utils.PageNation;
import com.mouse.dao.entity.user.CommentEntity;
import com.mouse.dao.entity.user.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author ; lidongdong
 * @Description 用户评论服务 API
 * @Date 2020-01-12
 */
@Slf4j
@Validated
@RestController
@RequestMapping("comment")
public class CommentClient extends GlobalExceptionHandler implements CommentFeign {

    @Autowired
    UserService userService;
    @Autowired
    CommentService commentService;

    @Override
    public R post(@RequestParam(name = "userId") String userId, @RequestBody SaveCommentReq param) {
        param.setUserId(userId);
        commentService.save(param);
        return R.success();
    }

    @Override
    public R count(@RequestParam(name = "type") Integer type,
                   @RequestParam(name = "valueId") Integer valueId) {

        int allCount = commentService.countByValueIdAndType(valueId, type);
        int hasPicCount = commentService.countByValueIdAndTypeAndHasPicture(valueId, type, true);

        Map<String, Object> entity = new HashMap<>(8);
        entity.put("allCount", allCount);
        entity.put("hasPicCount", hasPicCount);
        return R.success();
    }

    @Override
    public R findPage(@RequestParam(name = "valueId") Integer valueId,
                      @RequestParam(name = "type") Integer type,
                      @RequestParam(name = "showType") Integer showType,
                      @Min(value = 0, message = "必须从0页开始")
                      @RequestParam(name = "pageNum", defaultValue = "0", required = false) Integer pageNum,
                      @Min(value = 1, message = "每页必须大于1")
                      @Max(value = 300, message = "每页必须小于300")
                      @RequestParam(name = "pageSize", defaultValue = "20", required = false) Integer pageSize) {

        Page<CommentEntity> page = commentService.findPage(valueId, type, showType, pageNum, pageSize);
        List<Map<String, Object>> result = new ArrayList<>(pageSize);
        List<CommentEntity> content = page.getContent();

        if (!CollectionUtils.isEmpty(content)) {
            List<String> userIds = content.stream().map(CommentEntity::getUserId).collect(Collectors.toList());
            List<UserEntity> userEntities = userService.findByIdIn(userIds).orElseGet(() -> new ArrayList<>());
            Map<String, UserEntity> userMap = userEntities.stream().collect(Collectors.toMap(UserEntity::getId, a -> a, (k1, k2) -> k1));
            for (CommentEntity comment : content) {
                Map<String, Object> commentVo = new HashMap<>(16);
                commentVo.put("addTime", comment.getAddTime());
                commentVo.put("content", comment.getContent());
                commentVo.put("picList", comment.getPicUrls());
                commentVo.put("userInfo", userMap.get(comment.getUserId()));
                commentVo.put("reply", null);
                List<CommentEntity> commentEntities = commentService.findByValueIdAndType(comment.getId(), CommentTypeEnum.GOODS_TYPE).orElseGet(() -> new ArrayList<>());
                // 目前业务只支持回复一次
                if (commentEntities.size() == 1) {
                    commentVo.put("reply", commentEntities.get(0).getContent());
                }
                result.add(commentVo);
            }
        }
        return R.success(PageNation.of(page, result));
    }
}
