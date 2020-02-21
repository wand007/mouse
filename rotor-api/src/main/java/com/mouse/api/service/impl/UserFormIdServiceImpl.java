package com.mouse.api.service.impl;

import com.mouse.api.service.UserFormIdService;
import com.mouse.dao.repository.user.UserFormIdRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ; lidongdong
 * @Description
 * @Date 2020-02-21
 */
@Slf4j
@Service
public class UserFormIdServiceImpl implements UserFormIdService {
    @Autowired
    UserFormIdRepository userFormIdRepository;
}
