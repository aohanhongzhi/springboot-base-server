package hxy.base.server.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

/**
 * 国际化消息
 * <p>
 * https://blog.csdn.net/javahighness/article/details/92674878
 * https://blog.csdn.net/qq_43116655/article/details/110070703
 */
public class I18nMessage {

    private static final Logger LOGGER = LoggerFactory.getLogger(I18nMessage.class);

    private static MessageSource messageSource;

    /**
     * 初始化
     *
     * @return
     */
    private static MessageSource initMessageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("i18n/messages");
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setCacheSeconds(3600);
        return messageSource;
    }


    public static String getMessage(String code) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return getMessage(request, code);
    }

    /**
     * @param request
     * @param code
     * @return 返回国际化信息，默认是中文
     */
    public static String getMessage(HttpServletRequest request, String code) {
        if (messageSource == null) {
            messageSource = initMessageSource();
        }

        String language = request.getHeader("language");
        if (language == null) {
            language = request.getHeader("Content-Language");
        }
        Locale locale = request.getLocale();
        if ("en".equals(language)) {
            locale = Locale.ENGLISH;
        } else if ("zh".equals(language)) {
            locale = Locale.CHINA;
        }

        String result = null;
        try {
            result = messageSource.getMessage(code, null, locale);
        } catch (NoSuchMessageException e) {
            LOGGER.error("Cannot find the error message of internationalization, return the original error message.");
        }
        if (result == null) {
            return code;
        }
        return result;
    }
}

