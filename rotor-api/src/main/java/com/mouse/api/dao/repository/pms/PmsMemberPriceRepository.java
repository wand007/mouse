package com.mouse.api.dao.repository.pms;

import com.mouse.api.dao.entity.pms.PmsMemberPriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author ; lidongdong
 * @Description 商品会员价格表
 * @Date 2019-11-26
 */
@Repository
public interface PmsMemberPriceRepository extends JpaRepository<PmsMemberPriceEntity, Long>, JpaSpecificationExecutor<PmsMemberPriceEntity> {
}