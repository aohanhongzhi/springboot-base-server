package hxy.base.server.entity.enums;


import hxy.base.server.util.I18nMessage;

/**
 * @author eric
 * @program data
 * @description 基础返回状态枚举
 * @date 2021/10/12
 */
public enum BaseStatusCodeEnum implements BaseEnum<String, String> {
    /**
     * 返回成功信息
     */
    SUCCESS("H0000", "成功"),
    /**
     * 返回失败信息
     */
    ERROR("H0001", "错误"),
    /**
     * 没有找到
     */
    NOT_FOUND("H0002", "没有找到");

    private String code;
    /**
     * 这里的description可能是多余的，或者去用作读取messages的key。但还是比较冗余
     */
    @Deprecated
    private String description;

    BaseStatusCodeEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }

    @Override
    public String code() {
        return this.code;
    }

    @Override
    public String description() {
        return I18nMessage.getMessage(SUCCESS.name());
//        return this.description;
    }
}
