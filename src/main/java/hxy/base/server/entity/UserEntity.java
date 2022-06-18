package hxy.base.server.entity;

import hxy.base.server.config.jackson.serializer.SensitiveWrapped;
import hxy.base.server.entity.enums.SensitiveEnum;
import lombok.Data;

@Data
public class UserEntity {
    /**
     * 用户ID
     */
    private int userId;

    /**
     * 用户姓名
     */
    private String name;

    /**
     * 手机号
     */
    @SensitiveWrapped(SensitiveEnum.MOBILE_PHONE)
    private String mobile;

    /**
     * 身份证号码
     */
    @SensitiveWrapped(SensitiveEnum.ID_CARD)
    private String idCard;

    /**
     * 年龄
     */
    private String sex;

    /**
     * 性别
     */
    private int age;

    /**
     * 在jdk的序列化规则里 static和transient 应该不会被序列化
     */
    public static String staticName = "这个应该不会被序列化";

    // Jackson默认序列化 public的属性和getter方法
    public String getStaticName() {
        return staticName;
    }

    //  经过测试Set并不会影响序列化
    public void setStaticName(String staticName) {
        staticName = staticName;
    }

}
