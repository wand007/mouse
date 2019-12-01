package com.mouse.api.dao.repository.user;


import com.mouse.api.dao.entity.user.UserRecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


/**
 * @author ; lidongdong
 * @Description 用户提现记录
 * @Date 2019-11-26
 */
@Repository
public interface UserRecordRepository extends JpaRepository<UserRecordEntity, Integer>, JpaSpecificationExecutor<UserRecordEntity> {
}