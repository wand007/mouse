package com.mouse.api.client;

import com.mouse.api.base.BaseClient;
import com.mouse.api.feign.BrandFeign;
import com.mouse.api.service.BrandService;
import com.mouse.core.base.R;
import com.mouse.dao.entity.resource.BrandEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

/**
 * @author ; lidongdong
 * @Description 首页服务 API
 * @Date 2019-12-15
 */
@Slf4j
@Validated
@RestController
@RequestMapping("brand")
public class BrandClient extends BaseClient implements BrandFeign {

    @Autowired
    BrandService brandService;

    @Override
    public R findPage(Integer pageNum, Integer pageSize, String sort, String order) {
        Page<BrandEntity> page = brandService.findPage(pageNum, pageSize);
        List<BrandEntity> content = page.getContent();
        return R.success(content);
    }

    @Override
    public R findDetail(Integer id) {
        Optional<BrandEntity> brandEntityOptional = brandService.findById(id);
        if (!brandEntityOptional.isPresent()) {
            return R.success();
        }
        return R.success(brandEntityOptional.get());
    }
}
