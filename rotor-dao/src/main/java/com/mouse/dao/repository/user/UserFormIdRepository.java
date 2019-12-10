package com.mouse.dao.repository.user;

import com.mouse.dao.entity.user.UserFormIdEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author ; lidongdong
 * @Description
 * @Date 2019-11-26
 */
@Repository
public interface UserFormIdRepository extends JpaRepository<UserFormIdEntity, Integer>, JpaSpecificationExecutor<UserFormIdEntity> {
}