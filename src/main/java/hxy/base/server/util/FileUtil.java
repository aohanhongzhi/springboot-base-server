package hxy.base.server.util;

import java.io.File;

/**
 * @author eric
 * @program base-server
 * @description
 * @date 2022/2/16
 */
public class FileUtil {

    /**
     * @return 当前系统用户的home
     */
    public static String getHome() {
        return System.getProperty("user.dir") + File.separator;
    }
}
