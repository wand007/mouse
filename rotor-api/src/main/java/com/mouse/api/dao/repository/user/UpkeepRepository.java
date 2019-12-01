package com.mouse.api.dao.repository.user;

import com.mouse.api.dao.entity.user.UpkeepEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


/**
 * @author ; lidongdong
 * @Description 客户维护历史
 * @Date 2019-11-26
 */
@Repository
public interface UpkeepRepository extends JpaRepository<UpkeepEntity, Integer>, JpaSpecificationExecutor<UpkeepEntity> {

}
