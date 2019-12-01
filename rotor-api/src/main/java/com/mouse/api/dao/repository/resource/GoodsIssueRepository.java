package com.mouse.api.dao.repository.resource;

import com.mouse.api.dao.entity.resource.GoodsIssueEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


/**
 * @author ; lidongdong
 * @Description 商品咨询
 * @Date 2019-11-26
 */
@Repository
public interface GoodsIssueRepository extends JpaRepository<GoodsIssueEntity, Integer>, JpaSpecificationExecutor<GoodsIssueEntity> {


}
