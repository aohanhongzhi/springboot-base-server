package hxy.base.server.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import hxy.base.server.config.jackson.serializer.BaseEnumDeserializer;
import hxy.base.server.config.jackson.serializer.BaseEnumSerializer;
import hxy.base.server.config.jackson.serializer.BaseLongSerializer;
import hxy.base.server.config.jackson.serializer.DateJsonDeserializer;
import hxy.base.server.config.jackson.serializer.DateJsonSerializer;
import hxy.base.server.config.jackson.serializer.SimpleDeserializersWrapper;
import hxy.base.server.entity.enums.BaseEnum;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

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

    @Bean
    public ObjectMapper objectMapper(Jackson2ObjectMapperBuilder builder) {
        SimpleModule simpleModule = new SimpleModule();

        SimpleDeserializersWrapper deserializers = new SimpleDeserializersWrapper();
        deserializers.addDeserializer(BaseEnum.class, new BaseEnumDeserializer());
        deserializers.addDeserializer(Date.class, new DateJsonDeserializer());
        simpleModule.setDeserializers(deserializers);

        simpleModule.addSerializer(BaseEnum.class, new BaseEnumSerializer());
        simpleModule.addSerializer(Date.class, new DateJsonSerializer());

        // 超过浏览器处理精度的Long类型转成String给前端
        simpleModule.addSerializer(Long.class, new BaseLongSerializer());
        simpleModule.addSerializer(Long.TYPE, new BaseLongSerializer());

        ObjectMapper objectMapper = builder.createXmlMapper(false).build();
        objectMapper.registerModule(simpleModule);

        // 配置忽略未知属性
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        return objectMapper;
    }

}
