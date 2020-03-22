package com.springcloud.alibaba;

import com.baomidou.mybatisplus.autoconfigure.MybatisPlusAutoConfiguration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;

/**
 * @author 飞翔的胖哥
 * @version 1.0.0
 * @description mybatis审计字段自动装配
 * @since 2020/3/21 0021 22:06
 **/
@Configuration
@ConditionalOnBean(SqlSessionFactory.class)
@AutoConfigureAfter(MybatisPlusAutoConfiguration.class)
public class MybatisPlusAuditAutoConfiguration {

    @Resource
    private MybatisPlusAuditInterceptor mybatisPlusAuditInterceptor;

    @Resource
    private List<SqlSessionFactory> sqlSessionFactories;

    @PostConstruct
    public void addInterceptor(){
        for(SqlSessionFactory sqlSessionFactory:sqlSessionFactories){
            sqlSessionFactory.getConfiguration().addInterceptor(mybatisPlusAuditInterceptor);
        }
    }

}
