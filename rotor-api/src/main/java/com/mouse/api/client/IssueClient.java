package com.mouse.api.client;

import com.mouse.api.base.BaseClient;
import com.mouse.api.feign.IssueFeign;
import com.mouse.api.service.IssueService;
import com.mouse.api.service.OrderService;
import com.mouse.core.base.R;
import com.mouse.dao.entity.sys.IssueEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * @author ; lidongdong
 * @Description 用户服务 API
 * @Date 2019-12-15
 */
@Slf4j
@Validated
@RestController
@RequestMapping("issue")
public class IssueClient extends BaseClient implements IssueFeign {

    @Autowired
    IssueService issueService;

    @Override
    public R findPage(@RequestParam(defaultValue = "question", required = false) String question,
                      @Min(value = 0, message = "必须从0页开始")
                      @RequestParam(name = "pageNum", defaultValue = "0", required = false) Integer pageNum,
                      @Min(value = 1, message = "每页必须大于1")
                      @Max(value = 300, message = "每页必须小于300")
                      @RequestParam(name = "pageSize", defaultValue = "20", required = false) Integer pageSize,
                      @RequestParam(defaultValue = "add_time", required = false) String sort,
                      @RequestParam(defaultValue = "desc", required = false) String order) {
        Page<IssueEntity> page = issueService.findPage(question, pageNum, pageSize);

        return R.success(page.getContent());
    }
}
