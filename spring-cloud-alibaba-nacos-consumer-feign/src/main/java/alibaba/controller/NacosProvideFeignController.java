package alibaba.controller;

import alibaba.service.NacosProvideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author 飞翔的胖哥
 * @version 1.0.0
 * @description TODO
 * @since 2020/2/16 0016 19:32
 **/
@RestController
public class NacosProvideFeignController {

    @Resource
    private NacosProvideService nacosProvideService;

    @GetMapping(value = "/say")
    public String say(){
        return "晚上好 ，周伟老师 ！";

    }

    @GetMapping(value = "/consumer/feign")
    public String sayHi(){
        return nacosProvideService.say("zhouwei 666");

    }

}
