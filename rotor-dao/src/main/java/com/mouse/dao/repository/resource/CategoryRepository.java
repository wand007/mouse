package com.mouse.dao.repository.resource;

import com.mouse.dao.entity.resource.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author ; lidongdong
 * @Description
 * @Date 2019-11-26
 */
@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Integer>, JpaSpecificationExecutor<CategoryEntity> {

}