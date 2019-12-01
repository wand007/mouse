package com.mouse.api.dao.repository.user;

import com.mouse.api.dao.entity.user.UserMlsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


/**
 * @author ; lidongdong
 * @Description 分销用户
 * @Date 2019-11-26
 */
@Repository
public interface UserMlsRepository extends JpaRepository<UserMlsEntity, Integer>, JpaSpecificationExecutor<UserMlsEntity> {


}