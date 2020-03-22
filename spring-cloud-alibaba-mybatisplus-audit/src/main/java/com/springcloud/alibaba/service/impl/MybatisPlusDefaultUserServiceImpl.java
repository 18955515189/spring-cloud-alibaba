package com.springcloud.alibaba.service.impl;

import com.springcloud.alibaba.service.MybatisPlusDefaultUserService;
import org.slf4j.MDC;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Service;

/**
 * @author 飞翔的胖哥
 * @version 1.0.0
 * @description TODO
 * @since 2020/1/23 0023 10:17
 **/
@Service
public class MybatisPlusDefaultUserServiceImpl implements MybatisPlusDefaultUserService<String> {
    @Override
    public String getUser() {
        return MDC.get("user.loginName")==null?"system":MDC.get("user.loginName");
    }
}
