package com.mouse.api.service;

import com.mouse.dao.entity.operate.CouponEntity;
import com.mouse.dao.entity.resource.GoodsEntity;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

/**
 * @author ; lidongdong
 * @Description
 * @Date 2019-12-15
 */
public interface GoodsService {
    /**
     * 查询新品和在售的商品
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    Page<GoodsEntity> findByIsNewAndIsOnSaleAndPage(Integer pageNum, Integer pageSize);

    /**
     * 查询热销商品
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    Page<GoodsEntity> findByIsHot(Integer pageNum, Integer pageSize);

    /**
     * 根据类目ID集合 查询商品类目集合
     *
     * @param categoryIds 类目ID集合
     * @param pageNum
     * @param pageSize
     * @return
     */
    Page<GoodsEntity> findByCategory(List<Integer> categoryIds, Integer pageNum, Integer pageSize);

    /**
     * @param categoryId 分类类目ID，可选
     * @param brandId    品牌商ID，可选
     * @param keyword    关键字，可选
     * @param isNew      是否新品，可选
     * @param isHot      是否热买，可选
     * @param pageNum
     * @param pageSize
     * @param sort       排序方式，支持"add_time", "retail_price"或"name"
     * @param order      排序类型，顺序或者降序
     * @return
     */
    Page<GoodsEntity> findPage(Integer categoryId, Integer brandId, String keyword, Boolean isHot, Boolean isNew, Integer pageNum, Integer pageSize, String sort, String order);

    List<GoodsEntity> findList(Integer brandId, String keyword, Boolean isHot, Boolean isNew);

    Optional<GoodsEntity> findById(Integer id);

    Page<GoodsEntity> findByCategoryIdPage(Integer categoryId, Integer pageNum, Integer pageSize);

    Integer countByIsOnSale();

    Optional<GoodsEntity> findByIdAndIsOnSale(String goodsId);
}
