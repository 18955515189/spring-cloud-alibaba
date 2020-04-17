package com.springcloud.alibaba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author 飞翔的胖哥
 * @version 1.0.0
 * @description TODO
 * @since 2020/4/16 0016 14:06
 **/
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class BConsumeraApplication {

    public static void main(String[] args){
        SpringApplication.run(BConsumeraApplication.class,args);
    }

}
