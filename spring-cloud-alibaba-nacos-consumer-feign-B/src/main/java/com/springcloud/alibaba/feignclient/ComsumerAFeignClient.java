package com.springcloud.alibaba.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author 飞翔的胖哥
 * @version 1.0.0
 * @description TODO
 * @since 2020/4/16 0016 14:59
 **/
@FeignClient(name="nacos-consumer-feign-A",fallback = ComsumerAFeignClientFallBack.class)
public interface ComsumerAFeignClient {

    @GetMapping("/getA")
    public String getA();

    @GetMapping("/addFromAuthor/{id}")
    public String addFromAuthor(@PathVariable("id") String id);
    @GetMapping("/addFromAuthor2/{id}")
    public String addFromAuthor2(@PathVariable("id") String id);
}
