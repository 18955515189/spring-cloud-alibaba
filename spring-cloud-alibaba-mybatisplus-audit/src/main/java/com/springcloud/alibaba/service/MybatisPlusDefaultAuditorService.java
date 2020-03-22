package com.springcloud.alibaba.service;

/**
 * @author 飞翔的胖哥
 * @version 1.0.0
 * @description TODO
 * @since 2020/1/23 0023 10:20
 **/
public interface MybatisPlusDefaultAuditorService {

    void auditCreate(Object object);

    void auditUpdate(Object object);


}
