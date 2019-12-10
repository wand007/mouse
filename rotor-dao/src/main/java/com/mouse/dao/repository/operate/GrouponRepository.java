package com.mouse.dao.repository.operate;

import com.mouse.dao.entity.operate.GrouponEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author ; lidongdong
 * @Description
 * @Date 2019-11-26
 */
@Repository
public interface GrouponRepository extends JpaRepository<GrouponEntity, Integer>, JpaSpecificationExecutor<GrouponEntity> {

}