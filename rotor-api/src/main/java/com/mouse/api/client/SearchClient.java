package com.mouse.api.client;

import com.mouse.api.base.BaseClient;
import com.mouse.api.feign.SearchFeign;
import com.mouse.api.service.KeywordService;
import com.mouse.api.service.SearchHistoryService;
import com.mouse.core.base.R;
import com.mouse.dao.entity.sys.KeywordEntity;
import com.mouse.dao.entity.sys.SearchHistoryEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.*;

/**
 * @author ; lidongdong
 * @Description 商品搜索服务 API
 * @Date 2019-12-15
 */
@Slf4j
@Validated
@RestController
@RequestMapping("search")
public class SearchClient extends BaseClient implements SearchFeign {


    @Autowired
    KeywordService keywordsService;
    @Autowired
    SearchHistoryService searchHistoryService;

    @Override
    public R index(Integer userId) {
        //取出输入框默认的关键词
        KeywordEntity defaultKeyword = keywordsService.findDefault().orElseGet(() -> new KeywordEntity());
        //取出热闹关键词
        List<KeywordEntity> hotKeywordList = keywordsService.findIsHots().orElseGet(() -> Arrays.asList());

        List<SearchHistoryEntity> historyList = new ArrayList<>(0);
        if (userId != null) {
            //取出用户历史关键字
            historyList = searchHistoryService.findByUid(userId).orElseGet(() -> Arrays.asList());
        }

        Map<String, Object> data = new HashMap<String, Object>(8);
        data.put("defaultKeyword", defaultKeyword);
        data.put("historyKeywordList", historyList);
        data.put("hotKeywordList", hotKeywordList);
        return R.success();
    }

    @Override
    public R helper(@RequestParam(name = "keyword") String keyword,
                    @Min(value = 0, message = "必须从0页开始")
                    @RequestParam(name = "pageNum", defaultValue = "0", required = false) Integer pageNum,
                    @Min(value = 1, message = "每页必须大于1")
                    @Max(value = 300, message = "每页必须小于300")
                    @RequestParam(name = "pageSize", defaultValue = "20", required = false) Integer pageSize) {
        Page<KeywordEntity> page = keywordsService.findByKeyword(keyword, pageNum, pageSize);
        List<KeywordEntity> content = page.getContent();
        String[] keys = new String[content.size()];
        int index = 0;
        for (KeywordEntity key : content) {
            keys[index++] = key.getKeyword();
        }
        return R.success();
    }

    @Override
    public R clearhistory(Integer userId) {
        searchHistoryService.deleteByUserId(userId);
        return R.success();
    }
}
