package com.mouse.api.client;

import com.mouse.api.base.BaseClient;
import com.mouse.api.commons.FootprintComm;
import com.mouse.api.commons.GoodsComm;
import com.mouse.api.commons.enums.CategoryLevelEnum;
import com.mouse.api.commons.enums.RefererEnum;
import com.mouse.api.feign.GoodsFeign;
import com.mouse.api.service.*;
import com.mouse.api.system.SystemConfig;
import com.mouse.core.base.BusinessException;
import com.mouse.core.base.R;
import com.mouse.core.enums.CommentTypeEnum;
import com.mouse.core.utils.PageNation;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author ; lidongdong
 * @Description 用户购物车服务 API
 * @Date 2019-12-15
 */
@Slf4j
@Validated
@RestController
@RequestMapping("goods")
public class GoodClient extends BaseClient implements GoodsFeign {


    @Autowired
    GoodsComm goodsComm;
    @Autowired
    FootprintComm footprintComm;

    @Autowired
    UserService userService;
    @Autowired
    SearchHistoryService searchHistoryService;
    @Autowired
    BrandService brandService;
    @Autowired
    GoodsService goodsService;
    @Autowired
    GoodsAttributeService goodsAttributeService;
    @Autowired
    GoodsSpecificationService goodsSpecificationService;
    @Autowired
    ProductService productService;
    @Autowired
    GoodsIssueService goodsIssueService;
    @Autowired
    CommentService commentService;

    @Autowired
    GrouponRulesService grouponRulesService;
    @Autowired
    CollectService collectService;
    @Autowired
    CategoryService categoryService;


    /**
     * 商品详情
     * <p>
     * 用户可以不登录。
     * 如果用户登录，则记录用户足迹以及返回用户收藏信息。
     *
     * @param userId 用户ID
     * @param id     商品ID
     * @return 商品详情
     */
    @Override
    public R detail(@RequestParam(value = "userId", required = false) String userId,
                    @RequestParam("id") Integer id) {
        // 商品信息
        GoodsEntity goodsEntity = goodsService.findById(id).orElseThrow(() -> new BusinessException("商品不存在"));

        // 商品属性
        List<GoodsAttributeEntity> goodsAttributeEntities = goodsAttributeService.findByGoodsId(id).orElseThrow(() -> new BusinessException("商品属性记录不存在"));

        // 商品规格 返回的是定制的GoodsSpecificationVo
        List<GoodsSpecificationEntity> goodsSpecificationEntities = goodsSpecificationService.findByGoodsId(id).orElseThrow(() -> new BusinessException("商品规格记录不存在"));

        // 商品规格对应的数量和价格
        List<GoodsProductEntity> goodsProductEntities = productService.findByGoodsId(id).orElseThrow(() -> new BusinessException("商品规格记录不存在"));

        // 商品问题，这里是一些通用问题
        Page<IssueEntity> goodsIssuePage = goodsIssueService.findByquestionPage("", 1, 4, "", "");

        // 商品品牌商
        BrandEntity brandEntity = brandService.findById(goodsEntity.getBrandId()).orElseThrow(() -> new BusinessException("商品品牌记录存不在"));

        //团购信息
        List<GrouponRulesEntity> grouponRulesEntities = grouponRulesService.findByGoodsId(id).orElseGet(() -> new ArrayList<>());

        // 评论
        List<CommentEntity> commentEntities = commentService.findByValueIdAndType(id, CommentTypeEnum.PRODUCT_TYPE).orElseGet(() -> new ArrayList<>());
        List<Map<String, Object>> maps = new ArrayList<>(commentEntities.size());
        for (CommentEntity commentEntity : commentEntities) {
            Map<String, Object> c = new HashMap<>();
            c.put("id", commentEntity.getId());
            c.put("addTime", commentEntity.getAddTime());
            c.put("content", commentEntity.getContent());
            UserEntity userEntity = userService.findById(commentEntity.getUserId()).orElseGet(() -> new UserEntity());
            c.put("nickname", userEntity == null ? "" : userEntity.getNickName());
            c.put("avatar", userEntity == null ? "" : userEntity.getAvatar());
            c.put("picList", commentEntity.getPicUrls());
            maps.add(c);
        }
        Map<String, Object> commentList = new HashMap<>();
        commentList.put("count", commentEntities.size());
        commentList.put("data", maps);

        // 用户收藏
        int userHasCollect = 0;
        if (userId != null) {
            userHasCollect = collectService.countByUserIdAndValueId(userId, id);
        }

        // 记录用户的足迹 异步处理
        footprintComm.asyncSave(userId, id);

        Map<String, Object> data = new HashMap<>(16);

        data.put("info", goodsEntity);
        data.put("userHasCollect", userHasCollect);
        data.put("issue", goodsIssuePage.getContent());
        data.put("comment", commentEntities);
        data.put("specificationList", goodsSpecificationEntities);
        data.put("productList", goodsProductEntities);
        data.put("attribute", goodsAttributeEntities);
        data.put("brand", brandEntity);
        data.put("groupon", grouponRulesEntities);
        data.put("share", SystemConfig.isAutoCreateShareImage());
        //商品分享图片地址
        data.put("shareImage", goodsEntity.getShareUrl());
        return R.success(data);
    }


    /**
     * 商品分类类目
     *
     * @param id 分类类目ID
     * @return 商品分类类目
     */
    @Override
    public R category(@RequestParam("id") Integer id) {

        CategoryEntity categoryEntity = categoryService.findById(id).orElseThrow(() -> new BusinessException("商品类目记录不存在"));
        CategoryEntity parent = null;
        List<CategoryEntity> children = null;

        if (categoryEntity.getPid() == 0) {
            parent = categoryEntity;
            children = categoryService.findByPid(categoryEntity.getId()).orElseGet(() -> new ArrayList<>());
            categoryEntity = children.size() > 0 ? children.get(0) : categoryEntity;
        } else {
            parent = categoryService.findById(categoryEntity.getPid()).orElseGet(() -> new CategoryEntity());
            children = categoryService.findByPid(categoryEntity.getPid()).orElseGet(() -> new ArrayList<>());
        }
        Map<String, Object> data = new HashMap<>();
        data.put("currentCategory", categoryEntity);
        data.put("parentCategory", parent);
        data.put("brotherCategory", children);
        return R.success(data);
    }


    /**
     * 根据条件搜素商品
     * <p>
     * 1. 这里的前五个参数都是可选的，甚至都是空
     * 2. 用户是可选登录，如果登录，则记录用户的搜索关键字
     *
     * @param categoryId 分类类目ID，可选
     * @param brandId    品牌商ID，可选
     * @param keyword    关键字，可选
     * @param isNew      是否新品，可选
     * @param isHot      是否热买，可选
     * @param userId     用户ID
     * @param pageNum    分页页数
     * @param pageSize   分页大小
     * @param sort       排序方式，支持"add_time", "retail_price"或"name"
     * @param order      排序类型，顺序或者降序
     * @return 根据条件搜素的商品详情
     */
    @Override
    public R findPage(
            @RequestParam("categoryId") Integer categoryId,
            @RequestParam("brandId") Integer brandId,
            @RequestParam("keyword") String keyword,
            @RequestParam("isNew") Boolean isNew,
            @RequestParam("isHot") Boolean isHot,
            @RequestParam("userId") String userId,
            @RequestParam(name = "referer") Integer referer,
            @Min(value = 0, message = "必须从0页开始")
            @RequestParam(name = "pageNum", defaultValue = "0", required = false) Integer pageNum,
            @Min(value = 1, message = "每页必须大于1")
            @Max(value = 300, message = "每页必须小于300")
            @RequestParam(name = "pageSize", defaultValue = "20", required = false) Integer pageSize,
            @RequestParam(name = "sort", defaultValue = "add_time", required = false) String sort,
            @RequestParam(name = "order", defaultValue = "desc", required = false) String order) {

        //添加到搜索历史
        if (userId != null && !StringUtils.isNotBlank(keyword)) {
            searchHistoryService.asyncSave(userId, keyword, RefererEnum.parse(referer));
        }

        //查询列表数据
        Page<GoodsEntity> goodsEntityPage = goodsService.findPage(categoryId, brandId, keyword, isHot, isNew, pageNum, pageSize, sort, order);

        // 查询商品所属类目列表。
        List<GoodsEntity> goodsEntities = goodsService.findByBrandIdAndIsHotAndIsNewAndKeyword(brandId, isHot, isNew, keyword);


        List<CategoryEntity> categoryList = null;
        if (!CollectionUtils.isEmpty(goodsEntities)) {
            List<Integer> categoryIds = goodsEntities.stream().map(GoodsEntity::getCategoryId).collect(Collectors.toList());
            categoryList = categoryService.findByLevelAndIdIn(CategoryLevelEnum.L2.getCode(), categoryIds).orElseGet(() -> new ArrayList<>());
        } else {
            categoryList = new ArrayList<>(0);
        }

        return R.success(PageNation.of(goodsEntityPage, categoryList));
    }


    /**
     * 商品详情页面“大家都在看”推荐商品
     *
     * @param id, 商品ID
     * @return 商品详情页面推荐商品
     */
    @Override
    public R related(@RequestParam("id") Integer id) {
        GoodsEntity goodsEntity = goodsService.findById(id).orElseThrow(() -> new BusinessException("商品记录不存在"));

        // 目前的商品推荐算法仅仅是推荐同类目的其他商品
        int cid = goodsEntity.getCategoryId();

        // 查找六个相关商品
        Page<GoodsEntity> goodsEntityPage = goodsService.findByCategoryIdPage(cid, 0, 6);
        return R.success(goodsEntityPage.getContent());
    }

    /**
     * 在售的商品总数
     *
     * @return 在售的商品总数
     */
    @Override
    public R count() {
        Integer goodsCount = goodsService.countByIsOnSale();
        return R.success(goodsCount);
    }
}
