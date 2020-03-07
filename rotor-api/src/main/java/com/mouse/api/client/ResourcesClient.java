package com.mouse.api.client;

import com.mouse.api.base.BaseClient;
import com.mouse.api.commons.FootprintComm;
import com.mouse.api.commons.GoodsComm;
import com.mouse.api.commons.enums.RefererEnum;
import com.mouse.api.feign.ResourcesFeign;
import com.mouse.api.service.*;
import com.mouse.api.system.SystemConfig;
import com.mouse.core.base.BusinessException;
import com.mouse.core.base.R;
import com.mouse.dao.entity.operate.GrouponRulesEntity;
import com.mouse.dao.entity.resource.*;
import com.mouse.dao.entity.sys.IssueEntity;
import com.mouse.dao.entity.user.CommentEntity;
import com.mouse.dao.entity.user.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author ; lidongdong
 * @Description 资源 Api
 * @Date 2019-11-30
 */
@Slf4j
@Validated
@RestController
@RequestMapping("resources")
public class ResourcesClient extends BaseClient implements ResourcesFeign {

    @Autowired
    GoodsComm goodsComm;
    @Autowired
    FootprintComm footprintComm;
    @Autowired
    GoodsService goodsService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductService productService;
    @Autowired
    GoodsAttributeService goodsAttributeService;
    @Autowired
    GoodsSpecificationService goodsSpecificationService;
    @Autowired
    SearchHistoryService searchHistoryService;
    @Autowired
    GoodsIssueService goodsIssueService;
    @Autowired
    BrandService brandService;
    @Autowired
    CommentService commentService;
    @Autowired
    UserService userService;
    @Autowired
    GrouponRulesService rulesService;
    @Autowired
    CollectService collectService;
    @Autowired
    FootprintService footprintService;

    /**
     * 分页查询商品列表
     * 1. 这里的前五个参数都是可选的，甚至都是空
     * 2. 用户是可选登录，如果登录，则记录用户的搜索关键字
     *
     * @param categoryId 分类类目ID，可选
     * @param brandId    品牌商ID，可选
     * @param keyword    关键字，可选
     * @param isNew      是否新品，可选
     * @param isHot      是否热买，可选
     * @param userId     用户ID
     * @param referer    来源
     * @param pageNum
     * @param pageSize
     * @param sort       排序方式，支持"add_time", "retail_price"或"name"
     * @param order      排序类型，顺序或者降序
     * @return 根据条件搜素的商品详情
     */
    @Override
    public R findPage(@RequestParam(name = "categoryId", required = false) Integer categoryId,
                      @RequestParam(name = "brandId", required = false) Integer brandId,
                      @RequestParam(name = "keyword", required = false) String keyword,
                      @RequestParam(name = "isNew", required = false) Boolean isNew,
                      @RequestParam(name = "isHot", required = false) Boolean isHot,
                      @RequestParam(name = "userId", required = false) String userId,
                      @RequestParam(name = "referer") Integer referer,
                      @Min(value = 0, message = "必须从0页开始")
                      @RequestParam(name = "pageNum", defaultValue = "0", required = false) Integer pageNum,
                      @Min(value = 1, message = "每页必须大于1")
                      @Max(value = 300, message = "每页必须小于300")
                      @RequestParam(name = "pageSize", defaultValue = "20", required = false) Integer pageSize,
                      @RequestParam(name = "sort", defaultValue = "add_time", required = false) String sort,
                      @RequestParam(name = "order", defaultValue = "desc", required = false) String order){
        //添加到搜索历史
        if (userId != null && !StringUtils.isNotBlank(keyword)) {
            searchHistoryService.asyncSave(userId, keyword, RefererEnum.parse(referer));
        }

        //查询列表数据
        Page<GoodsEntity> page = goodsService.findPage(categoryId, brandId, keyword, isHot, isNew, pageNum, pageSize, sort, order);

        // 查询商品所属类目列表。
        List<GoodsEntity> goodsEntities = goodsService.findList(brandId, keyword, isHot, isNew);
        List<CategoryEntity> categoryList = new ArrayList<>(0);
        if (!CollectionUtils.isEmpty(goodsEntities)) {
            List<Integer> categoryIds = goodsEntities.stream().map(GoodsEntity::getCategoryId).collect(Collectors.toList());
            categoryList = categoryService.findByLevelAndIdIn("L2", categoryIds).orElseGet(() -> Arrays.asList());
        }

        Map<String, Object> entity = new HashMap<>();
        entity.put("list", page);
        entity.put("total", page.getTotalElements());
        entity.put("page", page.getNumber());
        entity.put("limit", page.getNumberOfElements());
        entity.put("pages", page.getContent());
        entity.put("filterCategoryList", categoryList);

        // 因为这里需要返回额外的filterCategoryList参数，因此不能方便使用ResponseUtil.okList
        return R.success(entity);
    }

    @Override
    public R detail(@RequestParam(name = "userId", required = false) String userId,
                    @RequestParam(name = "id") Integer id) {
        // 商品信息
        GoodsEntity info = goodsService.findById(id).orElseThrow(() -> {
            log.error("商品不存在id:{}", id);
            return new BusinessException("商品不存在");
        });

        // 商品属性
        List<GoodsAttributeEntity> goodsAttributeEntities = goodsAttributeService.findByGoodsId(id).orElseGet(() -> Arrays.asList());

        // 商品规格 返回的是定制的GoodsSpecificationVo
        List<GoodsSpecificationEntity> goodsSpecificationEntities = goodsSpecificationService.findByGoodsId(id).orElseGet(() -> Arrays.asList());

        // 商品规格对应的数量和价格
        List<GoodsProductEntity> goodsProductEntities = productService.findByGoodsId(id).orElseGet(() -> Arrays.asList());

        // 商品问题，这里是一些通用问题
        Page<IssueEntity> issueEntityPage = goodsIssueService.findByquestionPage("", 1, 4, "", "");

        // 商品品牌商
        List<BrandEntity> brandEntities = brandService.findByGoodsId(info.getBrandId());

        // 评论
        Page<CommentEntity> commentEntityPage = commentService.findPage(id, null, null, 0, 2);
        List<CommentEntity> content = commentEntityPage.getContent();
        List<Map<String, Object>> commentsVo = new ArrayList<>(content.size());
        for (CommentEntity comment : commentEntityPage) {
            Map<String, Object> c = new HashMap<>();
            c.put("id", comment.getId());
            c.put("addTime", comment.getAddTime());
            c.put("content", comment.getContent());
            UserEntity user = userService.findById(comment.getUserId()).orElseGet(() -> new UserEntity());
            c.put("nickname", user == null ? "" : user.getNickName());
            c.put("avatar", user == null ? "" : user.getAvatar());
            c.put("picList", comment.getPicUrls());
            commentsVo.add(c);
        }
        Map<String, Object> commentList = new HashMap<>();
        commentList.put("count", commentEntityPage.getTotalPages());
        commentList.put("data", commentsVo);

        //团购信息
        List<GrouponRulesEntity> grouponRulesEntities = rulesService.findByGoodsId(id).orElseGet(() -> Arrays.asList());

        // 用户收藏
        int userHasCollect = 0;
        if (userId != null) {
            userHasCollect = collectService.countByUserIdAndValueId(userId, id);
        }

        // 记录用户的足迹 异步处理
        if (userId != null) {
            footprintComm.asyncSave(userId, id);
        }

        Map<String, Object> data = new HashMap<>();

        data.put("info", info);
        data.put("userHasCollect", userHasCollect);
        data.put("issue", issueEntityPage.getContent());
        data.put("comment", commentList);
        data.put("specificationList", goodsSpecificationEntities);
        data.put("productList", goodsProductEntities);
        data.put("attribute", goodsAttributeEntities);
        data.put("brand", brandEntities);
        data.put("groupon", goodsAttributeEntities);
        //SystemConfig.isAutoCreateShareImage()
        data.put("share", SystemConfig.isAutoCreateShareImage());


        //商品分享图片地址
        data.put("shareImage", info.getShareUrl());
        return R.success(data);
    }

    @Override
    public R category(Integer id) {
        CategoryEntity categoryEntity = categoryService.findById(id).orElseGet(() -> new CategoryEntity());
        CategoryEntity parent = null;
        List<CategoryEntity> children = null;

        if (categoryEntity.getPid() == 0) {
            parent = categoryEntity;
            children = categoryService.findByPid(categoryEntity.getId()).orElseGet(() -> Arrays.asList());
            categoryEntity = children.size() > 0 ? children.get(0) : categoryEntity;
        } else {
            parent = categoryService.findById(categoryEntity.getPid()).orElseGet(() -> new CategoryEntity());
            children = categoryService.findByPid(categoryEntity.getPid()).orElseGet(() -> Arrays.asList());
        }
        Map<String, Object> data = new HashMap<>();
        data.put("currentCategory", categoryEntity);
        data.put("parentCategory", parent);
        data.put("brotherCategory", children);
        return R.success(data);
    }

    @Override
    public R related(Integer id) {
        //目前的商品推荐算法仅仅是推荐同类目的其他商品
        Optional<GoodsEntity> goodsEntityOptional = goodsService.findById(id);
        if (!goodsEntityOptional.isPresent()) {
            return R.success();
        }
        // 查找六个相关商品
        int related = 6;
        Page<GoodsEntity> page = goodsService.findByCategoryIdPage(goodsEntityOptional.get().getCategoryId(), 0, related);
        return R.success(page.getContent());
    }

    @Override
    public R count() {
        Integer goodsCount = goodsService.countByIsOnSale();
        return R.success(goodsCount);
    }
}
