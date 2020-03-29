package com.mouse.api.service.impl;

import com.mouse.api.service.AftersaleService;
import com.mouse.dao.repository.order.AftersaleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ; lidongdong
 * @Description 售后服务
 * @Date 2019-12-15
 */
@Slf4j
@Service
public class AftersaleServiceImpl implements AftersaleService {
    @Autowired
    AftersaleRepository aftersaleRepository;

}
