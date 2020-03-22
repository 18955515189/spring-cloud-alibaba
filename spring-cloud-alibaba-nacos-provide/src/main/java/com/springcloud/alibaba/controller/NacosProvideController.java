package com.springcloud.alibaba.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 飞翔的胖哥
 * @version 1.0.0
 * @description TODO
 * @since 2020/2/16 0016 19:32
 **/
@RefreshScope
@RestController
public class NacosProvideController {

    @Value("${server.port}")
    private String serverPort;

    // 配置中心动态获取
    @Value("${test.msg}")
    private String msg;

    @GetMapping(value = "/say/{message}")
    public String say(@PathVariable(value = "message") String message){
        return " hello Nacos "+message+" i am from "+serverPort;
    }

    @GetMapping(value = "/get")
    public String get(){
        return " hello "+msg;
    }

}
