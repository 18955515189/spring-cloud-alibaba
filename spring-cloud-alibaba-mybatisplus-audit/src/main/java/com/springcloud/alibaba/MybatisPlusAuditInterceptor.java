package com.springcloud.alibaba;

import com.springcloud.alibaba.service.MybatisPlusDefaultAuditorService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Properties;
import java.util.function.Consumer;

/**
 * @author 飞翔的胖哥
 * @version 1.0.0
 * @description 拦截器，拦截所有的Update操作
 * @since 2020/3/21 0021 22:12
 **/
@Slf4j
@Component
@Intercepts({@Signature(type = Executor.class,method = "update",args = {MappedStatement.class,Object.class})})
public class MybatisPlusAuditInterceptor implements Interceptor {

    @Resource
    private MybatisPlusDefaultAuditorService mybatisPlusDefaultAuditorService;

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        log.info("start mybatis-plus audit ......");
        Object param = invocation.getArgs()[1];
        if(param instanceof MapperMethod.ParamMap){
            Object param1 = ((MapperMethod.ParamMap<?>)param).get("param1");
            if(param1 != null){
                param = param1;
            }
        }

        MappedStatement mappedStatement = (MappedStatement)invocation.getArgs()[0];
        SqlCommandType sqlCommandType = mappedStatement.getSqlCommandType();
        switch (sqlCommandType){
            case INSERT:
                audit(param,mybatisPlusDefaultAuditorService::auditCreate);
                break;
            case UPDATE:
                audit(param,mybatisPlusDefaultAuditorService::auditUpdate);
                break;
            default:
        }
        return invocation.proceed();
    }

    protected void audit(Object object, Consumer<Object> action){
        if(object instanceof Iterable){
            Iterable<Object> entities = (Iterable<Object>) object;
            entities.forEach(x -> audit(x,action));
        }else{
            action.accept(object);
        }
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target,this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
