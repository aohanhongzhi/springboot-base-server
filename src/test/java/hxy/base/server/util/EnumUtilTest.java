package hxy.base.server.util;

import hxy.base.server.entity.enums.BaseStatusCodeEnum;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author eric
 * @program base-server
 * @description
 * @date 2/15/22
 */
public class EnumUtilTest {

    @Test
    public void testEnum() {
        final String success = EnumUtil.getCodeByDescription(BaseStatusCodeEnum.values(), "成功");
        assertEquals(success, "H0000");
    }
}
