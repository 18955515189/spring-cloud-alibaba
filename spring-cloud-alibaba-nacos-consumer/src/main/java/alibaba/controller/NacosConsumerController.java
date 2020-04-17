package alibaba.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author 飞翔的胖哥
 * @version 1.0.0
 * @description TODO
 * @since 2020/2/16 0016 19:32
 **/
@RestController
@RefreshScope
public class NacosConsumerController {

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${spring.application.name}")
    private String applicationName;

    @GetMapping(value = "/consumer/say")
    public String say(){
        ServiceInstance instance = loadBalancerClient.choose("nacos-provide");
        String url = String.format("http://%s:%s/say/%s",instance.getHost(),instance.getPort(),applicationName);
        return restTemplate.getForObject(url,String.class);

    }

}
