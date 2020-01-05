package com.mouse.dao.repository.user;

import com.mouse.dao.entity.user.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author ; lidongdong
 * @Description
 * @Date 2019-11-27
 */
@Repository
public interface AddressRepository extends JpaRepository<AddressEntity, Integer>, JpaSpecificationExecutor<AddressEntity> {

    /**
     * 根据用户ID修改是否默认地址
     *
     * @param userId
     * @param isDefault
     * @return
     */
    @Modifying
    @Transactional(rollbackFor = Exception.class, timeout = 30, isolation = Isolation.READ_COMMITTED)
    @Query(value = "update AddressEntity r set r.isDefault = ?2, r.updateTime = now() where r.userId = ?1")
    Integer updateIsDefaultByUserId(Integer userId, Boolean isDefault);

    /**
     * 修改删除状态
     *
     * @param id
     * @param deleted
     * @return
     */
    @Modifying
    @Transactional(rollbackFor = Exception.class, timeout = 30, isolation = Isolation.READ_COMMITTED)
    @Query(value = "update AddressEntity r set r.deleted = ?2, r.updateTime = now() where r.id = ?1")
    Integer updateDeleteByIdAndUserId(Integer id, Boolean deleted);
}
