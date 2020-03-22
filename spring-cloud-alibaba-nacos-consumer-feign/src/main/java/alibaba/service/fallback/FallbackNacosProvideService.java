package alibaba.service.fallback;

import alibaba.service.NacosProvideService;
import org.springframework.stereotype.Component;

/**
 * @author 飞翔的胖哥
 * @version 1.0.0
 * @description TODO
 * @since 2020/2/17 0017 0:04
 **/
@Component
public class FallbackNacosProvideService implements NacosProvideService {
    @Override
    public String say(String message) {
        return "网络异常，请稍后再试！";
    }
}
