package com.mouse.api.dao.repository.user;

import com.mouse.api.dao.entity.user.UserLevelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


/**
 * @author ; lidongdong
 * @Description 会员等级
 * @Date 2019-11-26
 */
@Repository
public interface UserLevelRepository extends JpaRepository<UserLevelEntity, Integer>, JpaSpecificationExecutor<UserLevelEntity> {

}
