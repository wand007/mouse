package com.mouse.api.service.impl;

import com.mouse.api.commons.req.SaveAddressReq;
import com.mouse.api.service.AddressService;
import com.mouse.dao.entity.user.AddressEntity;
import com.mouse.dao.repository.user.AddressRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author ; lidongdong
 * @Description 用户收货地址服务
 * @Date 2019-12-15
 */
@Slf4j
@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    AddressRepository addressRepository;

    /**
     * 根据记录ID查询用户收货地址记录
     *
     * @param id 收货地址ID
     * @return
     */
    @Override
    public Optional<AddressEntity> findById(Integer id) {
        return addressRepository.findById(id);
    }

    /**
     * 根据记录ID和用户ID查询用户收货地址记录
     *
     * @param id     收货地址ID
     * @param userId 用户ID
     * @return
     */
    @Override
    public Optional<AddressEntity> findByIdAndUserId(Integer id, String userId) {
        AddressEntity product = new AddressEntity();
        product.setId(id);
        product.setUserId(userId);
        Example<AddressEntity> example = Example.of(product);
        return addressRepository.findOne(example);
    }

    /**
     * 分页列表查询
     *
     * @param userId   用户ID
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public Page<AddressEntity> findPage(String userId, Integer pageNum, Integer pageSize) {

        List<Sort.Order> orders = new ArrayList<>();
        orders.add(new Sort.Order(Sort.Direction.ASC, "isDefault"));
        orders.add(new Sort.Order(Sort.Direction.DESC, "id"));

        Page<AddressEntity> page = addressRepository.findAll((Specification<AddressEntity>) (root, criteriaQuery, criteriaBuilder) -> {

            Predicate predicate = criteriaBuilder.conjunction();
            List<Expression<Boolean>> expressions = predicate.getExpressions();
            expressions.add(criteriaBuilder.equal(root.<Integer>get("userId"), userId));
            expressions.add(criteriaBuilder.equal(root.<Boolean>get("deleted"), false));

            return predicate;
        }, PageRequest.of(pageNum, pageSize, Sort.by(orders)));

        return page;
    }

    /**
     * 新增/修改用户收货地址记录
     *
     * @param param
     */
    @Override
    @Transactional(rollbackFor = Exception.class, timeout = 30, isolation = Isolation.READ_COMMITTED)
    public void save(SaveAddressReq param) {

        AddressEntity addressEntity = addressRepository.findById(param.getId()).orElseGet(() -> {
            AddressEntity entity = new AddressEntity();
            entity.setUserId(param.getUserId());
            entity.setAddress(param.getAddress());
            entity.setAreaCode(param.getAreaCode());
            entity.setPostalCode(param.getPostalCode());
            entity.setCity(param.getCity());
            entity.setCounty(param.getCounty());
            entity.setIsDefault(param.getIsDefault());
            entity.setName(param.getName());
            entity.setProvince(param.getProvince());
            entity.setTel(param.getTel());
            entity.setDeleted(false);
            return entity;
        });
        if (param.getIsDefault()) {
            // 重置其他收货地址的默认选项
            addressRepository.updateIsDefaultByUserId(param.getUserId(), false);
        }
        addressRepository.save(addressEntity);

    }

    /**
     * 更新收货地址
     *
     * @param param 用户收货地址
     * @return 添加操作结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class, timeout = 30, isolation = Isolation.READ_COMMITTED)
    public void update(SaveAddressReq param) {
        AddressEntity addressEntity = addressRepository.findById(param.getId()).get();
        if (StringUtils.isNotBlank(param.getAddress())) {
            addressEntity.setAddress(param.getAddress());
        }
        if (StringUtils.isNotBlank(param.getCity())) {
            addressEntity.setCity(param.getCity());
        }
        if (StringUtils.isNotBlank(param.getProvince())) {
            addressEntity.setProvince(param.getProvince());
        }
        if (StringUtils.isNotBlank(param.getCounty())) {
            addressEntity.setCounty(param.getCounty());
        }
        if (StringUtils.isNotBlank(param.getName())) {
            addressEntity.setName(param.getName());
        }
        if (StringUtils.isNotBlank(param.getPostalCode())) {
            addressEntity.setPostalCode(param.getPostalCode());
        }
        if (StringUtils.isNotBlank(param.getTel())) {
            addressEntity.setTel(param.getTel());
        }
        if (param.getIsDefault() != null) {
            addressEntity.setIsDefault(param.getIsDefault());
        }
        if (addressEntity.getIsDefault()) {
            // 重置其他收货地址的默认选项
            addressRepository.updateIsDefaultByUserId(addressEntity.getUserId(), false);
        }

        addressRepository.save(addressEntity);
    }

    /**
     * 删除收货地址
     *
     * @param id 收货地址ID
     * @return 删除操作结果
     */
    @Override
    public void delete(Integer id) {
        addressRepository.updateDeleteByIdAndUserId(id, true);
    }
}
