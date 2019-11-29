package com.mouse.api.dao.repository;

import com.mouse.api.dao.entity.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author ; lidongdong
 * @Description
 * @Date 2019-11-27
 */
@Repository
public interface AddressRepository extends JpaRepository<AddressEntity, String>, JpaSpecificationExecutor<AddressEntity> {
}
