package com.mouse.api.client;

import com.mouse.api.base.BaseClient;
import com.mouse.api.commons.req.SaveAddressReq;
import com.mouse.api.commons.rsp.AddressRsp;
import com.mouse.api.feign.AddressFeign;
import com.mouse.api.service.AddressService;
import com.mouse.core.base.R;
import com.mouse.core.utils.MaskUtils;
import com.mouse.core.utils.PageNation;
import com.mouse.dao.entity.user.AddressEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author ; lidongdong
 * @Description 用户收货地址服务 API
 * @Date 2019-12-15
 */
@Slf4j
@Validated
@RestController
@RequestMapping("address")
public class AddressClient extends BaseClient implements AddressFeign {

    @Autowired
    AddressService addressService;

    /**
     * 用户收货地址列表
     *
     * @param userId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public R findPage(String userId, Integer pageNum, Integer pageSize) {

        List<AddressRsp> result = new ArrayList<>();

        Page<AddressEntity> page = addressService.findPage(userId, pageNum, pageSize);
        List<AddressEntity> content = page.getContent();
        if (!CollectionUtils.isEmpty(content)) {
            content.stream().forEach(addressEntity -> {
                AddressRsp addressRsp = new AddressRsp();
                addressRsp.setId(addressEntity.getId());
                addressRsp.setAddress(addressEntity.getAddress());
                addressRsp.setAreaCode(addressEntity.getAreaCode());
                addressRsp.setCity(addressEntity.getCity());
                addressRsp.setCounty(addressEntity.getCounty());
                addressRsp.setIsDefault(addressEntity.getIsDefault());
                addressRsp.setName(addressEntity.getName());
                addressRsp.setPostalCode(addressEntity.getPostalCode());
                addressRsp.setProvince(addressEntity.getProvince());
                addressRsp.setTel(MaskUtils.maskMobile(addressEntity.getTel()));
                result.add(addressRsp);
            });
        }
        return R.success(PageNation.of(page, result));
    }

    /**
     * 收货地址详情
     *
     * @param userId 用户ID
     * @param id     收货地址ID
     * @return 收货地址详情
     */
    @Override
    public R detail(String userId, Integer id) {
        AddressRsp addressRsp = new AddressRsp();

        Optional<AddressEntity> addressEntityOptional = addressService.findByIdAndUserId(id, userId);
        if (addressEntityOptional.isPresent()) {
            AddressEntity addressEntity = addressEntityOptional.get();
            addressRsp.setId(addressEntity.getId());
            addressRsp.setAddress(addressEntity.getAddress());
            addressRsp.setAreaCode(addressEntity.getAreaCode());
            addressRsp.setCity(addressEntity.getCity());
            addressRsp.setCounty(addressEntity.getCounty());
            addressRsp.setIsDefault(addressEntity.getIsDefault());
            addressRsp.setName(addressEntity.getName());
            addressRsp.setPostalCode(addressEntity.getPostalCode());
            addressRsp.setProvince(addressEntity.getProvince());
            addressRsp.setTel(MaskUtils.maskMobile(addressEntity.getTel()));
        }

        return R.success(addressRsp);
    }

    /**
     * 添加收货地址
     *
     * @param param 用户收货地址
     * @return 更新操作结果
     */
    @Override
    public R save(SaveAddressReq param) {
        addressService.save(param);
        return R.success();
    }

    /**
     * 更新收货地址
     *
     * @param param 用户收货地址
     * @return 添加操作结果
     */
    @Override
    public R update(SaveAddressReq param) {
        addressService.update(param);
        return R.success();
    }

    /**
     * 删除收货地址
     *
     * @param userId 用户ID
     * @param id     收货地址ID
     * @return 删除操作结果
     */
    @Override
    public R delete(String userId, Integer id) {
        addressService.delete(id);
        return R.success();
    }

}
