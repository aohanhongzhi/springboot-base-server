package hxy.base.server.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.context.request.RequestAttributes;
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
        final RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes != null) {
            HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
            return getMessage(request, code);
        } else {
            return getMessage(null, code);
        }
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
        Locale locale = Locale.CHINA;
        if (request != null) {
            String language = request.getHeader("language");
            if (language == null) {
                language = request.getHeader("Content-Language");
            }

            if ("en".equalsIgnoreCase(language)) {
                locale = Locale.ENGLISH;
            } else if ("zh".equalsIgnoreCase(language)) {
                locale = Locale.CHINA;
            } else {
                locale = request.getLocale() != null ? request.getLocale() : Locale.CHINA;
            }
        }


        String result = null;
        try {
            result = messageSource.getMessage(code, null, locale);
        } catch (NoSuchMessageException e) {
            LOGGER.error("Cannot find the error message of internationalization, return the original error message.");
        }

        return result;
    }
}

