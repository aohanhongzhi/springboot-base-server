package hxy.base.server.entity;

import hxy.base.server.entity.exception.BaseException;

/**
 * @author eric
 * @program base-server
 * @description 基础业务断言，可以有效减少if的判断
 * @date 2/15/22
 * @see org.springframework.util.Assert Spring默认异常是IllegalStateException 适合框架级别，不适合业务级别
 */
public class BaseAssert {
    public static void isTrue(boolean expression, String message) {
        if (!expression) {
            // 这里的异常可以由全局异常进一步处理
            throw new BaseException(message);
        }
    }
}
