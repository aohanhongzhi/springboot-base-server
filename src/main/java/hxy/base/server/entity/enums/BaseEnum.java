package hxy.base.server.entity.enums;

/**
 * @author eric
 * @program data
 * @description 枚举统一接口
 * @date 2021/10/12
 */
public interface BaseEnum<T, E> {
    /**
     * Code integer.
     *
     * @return the integer
     */
    T code();

    /**
     * Description string.
     *
     * @return the string
     */
    E description();

    /**
     * 反序列化需要实现
     *
     * @return
     */
    String name();
}
