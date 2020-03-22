package com.springcloud.alibaba.service.impl;

import com.springcloud.alibaba.service.MybatisPlusDefaultAuditorService;
import com.springcloud.alibaba.service.MybatisPlusDefaultUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.time.LocalDateTime;

/**
 * @author 飞翔的胖哥
 * @version 1.0.0
 * @description TODO
 * @since 2020/1/23 0023 10:22
 **/
@Slf4j
@Service
public class MybatisPlusDefaultAuditorServiceImpl implements MybatisPlusDefaultAuditorService {

    @Resource
    protected MybatisPlusDefaultUserService<String> mybatisPlusDefaultUserService;

    @Override
    public void auditCreate(Object object) {
        String user = getUser();
        LocalDateTime now = LocalDateTime.now();
        if(canFix(object,"createdBy"))tryInvoke(object,"createdBy",user);
        if(canFix(object,"createdDate"))tryInvoke(object,"createdDate",now);
        auditUpdate(object);
    }

    @Override
    public void auditUpdate(Object object) {
        String user = getUser();
        LocalDateTime now = LocalDateTime.now();
        if(canFix(object,"updatedBy"))tryInvoke(object,"updatedBy",user);
        if(canFix(object,"updatedDate"))tryInvoke(object,"updatedDate",now);
    }

    protected void tryInvoke(Object object, String fieldName,Object value) {
        try {
            Field field = object.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            Object objValue = field.get(object);
            if(objValue==null){
                field.set(object,value);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("mybatis-plus autoaudit 失败:"+e.getMessage());
        }

    }

    protected Boolean canFix(Object object,String fieldName){
        try {
            Field field = object.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            Object value = field.get(object);
            if(value==null){
                return true;
            }else{
                return false;
            }
        }catch (Exception e){
            return false;
        }
    }

    protected String getUser() {
        return mybatisPlusDefaultUserService.getUser();
    }

}
