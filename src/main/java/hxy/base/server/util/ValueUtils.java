package hxy.base.server.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author eric
 * @program base-server
 * @description 非Spring容器里面，获取到Spring注入的值
 * @date 2021/10/8
 */
@Component
public class ValueUtils {

    /**
     * Azure的bolb containerName
     */
    private static String container;

    /**
     * @return 非Spring容器里面，获取到Spring注入的值
     */
    public static String getContainer() {
        return container;
    }

    @Value("${spring.application.name}")
    public void setContainer(String container) {
        ValueUtils.container = container;
    }

}