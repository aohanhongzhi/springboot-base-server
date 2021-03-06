package hxy.base.server.config.jackson.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import hxy.base.server.entity.enums.BaseEnum;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class BaseEnumSerializer extends JsonSerializer<BaseEnum> {


    @Override
    public void serialize(BaseEnum value, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
        log.info("\n====>开始序列化[{}]", value);
        if (value != null) {
            gen.writeStartObject();
            Object code = value.code();
            if (code != null) {
                gen.writeObjectField("code", code);
            } else {
                log.warn("\n====>该枚举[{}]的code为空", value);
            }
            gen.writeObjectField("description", value.description());
            gen.writeEndObject();
        }
    }

}