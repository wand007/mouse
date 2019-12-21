package com.mouse.api.service;

import com.mouse.api.commons.enums.CategoryLevelEnum;
import com.mouse.dao.entity.resource.CategoryEntity;

import java.util.List;
import java.util.Optional;

/**
 * @author ; lidongdong
 * @Description
 * @Date 2019-12-15
 */
public interface CategoryService {
    /**
     * 根据类目等级查询类目
     *
     * @param categoryLevelEnum 类目等级
     * @return
     */
    Optional<List<CategoryEntity>> findByLevel(CategoryLevelEnum categoryLevelEnum);

    Optional<List<CategoryEntity>> findByLevelAndNameNotAnd(Integer pageNum, Integer pageSize);

    Optional<List<CategoryEntity>> findByPid(Integer pId);

    Optional<List<CategoryEntity>> findByLevelAndIdIn(String level, List<Integer> goodsCatIds);
}
