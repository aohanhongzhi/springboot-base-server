package hxy.base.server.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hxy.base.server.ServerApplicationTests;
import hxy.base.server.entity.UserEntity;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;

public class SensitiveJacksonTest extends ServerApplicationTests {


    @Resource
    ObjectMapper objectMapper;

    @Test
    public void test1() throws JsonProcessingException {
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(1);
        userEntity.setName("张三");
        userEntity.setMobile("18000000001");
        userEntity.setIdCard("420117200001011000008888");
        userEntity.setAge(20);
        userEntity.setSex("男");

        //通过jackson方式，将对象序列化成json字符串
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(objectMapper.writeValueAsString(userEntity));
    }
}
