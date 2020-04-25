package com.springcloud.alibaba;

import io.seata.spring.annotation.datasource.EnableAutoDataSourceProxy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author 飞翔的胖哥
 * @version 1.0.0
 * @description TODO
 * @since 2020/4/16 0016 14:05
 **/
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableDiscoveryClient
@EnableFeignClients
@EnableAutoDataSourceProxy
public class AConsumeraApplication {

    public static void main(String[] args){

        SpringApplication.run(AConsumeraApplication.class,args);

    }

}
