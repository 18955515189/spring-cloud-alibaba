package alibaba.service;

import alibaba.service.fallback.FallbackNacosProvideService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author 飞翔的胖哥
 * @version 1.0.0
 * @description TODO
 * @since 2020/2/16 0016 20:59
 **/
@FeignClient(value = "nacos-provide",fallback = FallbackNacosProvideService.class)
public interface NacosProvideService {

    @GetMapping(value = "/say/{message}")
    public String say(@PathVariable(value = "message") String message);
}
