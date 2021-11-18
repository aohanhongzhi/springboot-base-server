package hxy.base.server.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 请求相关处理类
 *
 * @author eric
 * @program base-server
 * @description
 * @date 2021/11/18
 */
public class RequestUtil {

    public static Object getAttributeInfo() {
        // 下面是静态方法直接处理。这里应该涉及到ThreadLocal的底层知识，需要好好研究下。
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Object user = request.getAttribute("user");
        return user;
    }

    /**
     * 将user 信息存在request中
     *
     * @param user
     */
    public <T> void setUser(T user) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        request.setAttribute("user", user);
    }


}
