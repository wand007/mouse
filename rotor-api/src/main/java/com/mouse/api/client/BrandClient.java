package com.mouse.api.client;

import com.mouse.api.GoodsComm;
import com.mouse.api.base.BaseClient;
import com.mouse.api.feign.BrandFeign;
import com.mouse.api.service.*;
import com.mouse.core.base.R;
import com.mouse.dao.entity.resource.BrandEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
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
    GoodsComm goodsComm;

    @Autowired
    AdService adService;
    @Autowired
    TopicService topicService;
    @Autowired
    GoodsService goodsService;
    @Autowired
    BrandService brandService;
    @Autowired
    CouponService couponService;
    @Autowired
    CategoryService categoryService;

    @Autowired
    GrouponService grouponService;

    @Override
    public R findPage(@Min(value = 0, message = "必须从0页开始")
                      @RequestParam(name = "pageNum", defaultValue = "0", required = false) Integer pageNum,
                      @Min(value = 1, message = "每页必须大于1")
                      @Max(value = 300, message = "每页必须小于300")
                      @RequestParam(name = "pageSize", defaultValue = "20", required = false) Integer pageSize,
                      @RequestParam(defaultValue = "add_time", required = false) String sort,
                      @RequestParam(defaultValue = "desc", required = false) String order) {
        Page<BrandEntity> page = brandService.findPage(pageNum, pageSize);
        List<BrandEntity> content = page.getContent();
        return R.success(content);
    }

    @Override
    public R detail(@NotNull Integer id) {
        Optional<BrandEntity> brandEntityOptional = brandService.findById(id);
        if (!brandEntityOptional.isPresent()) {
            return R.success();
        }
        return R.success(brandEntityOptional.get());
    }
}
