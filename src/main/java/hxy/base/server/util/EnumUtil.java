package hxy.base.server.util;

import hxy.base.server.entity.enums.BaseEnum;

/**
 * @author eric
 * @program base-server
 * @description 枚举的工具类
 * @date 2/15/22
 */
public class EnumUtil {

    public static String getDescriptionByCode(BaseEnum<String, String>[] baseEnums, Integer code) {
        for (BaseEnum<String, String> baseEnum : baseEnums) {
            if (baseEnum.code().equals(code)) {
                return baseEnum.description();
            }
        }
        return null;
    }

    public static String getCodeByDescription(BaseEnum<String, String>[] baseEnums, String description) {
        for (BaseEnum<String, String> baseEnum : baseEnums) {
            if (baseEnum.description().equals(description)) {
                return baseEnum.code();
            }
        }
        return null;
    }

}
