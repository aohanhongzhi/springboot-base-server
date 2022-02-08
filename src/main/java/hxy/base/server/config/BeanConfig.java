package hxy.base.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author eric
 * @program base-server
 * @description Bean的配置
 * @date 2/8/22
 */
@Configuration
public class BeanConfig {

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
