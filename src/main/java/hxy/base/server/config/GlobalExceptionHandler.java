package hxy.base.server.config;

import hxy.base.server.entity.BaseResponse;
import hxy.base.server.entity.exception.BaseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ValidationException;

/**
 * @author eric
 * @program inspector-server
 * @description
 * @date 2021/11/2
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler implements InitializingBean {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = Exception.class)
    public BaseResponse exceptionHandler(HttpServletRequest request, Exception exception) {
        String message = exception.getMessage();
        log.error("{} Exception Message: {}", request.getRequestURI(), message, exception);

        if (exception instanceof NullPointerException) {
            message = "biu，踩雷啦！";
        } else if (exception instanceof ValidationException) {
            message = "参数检验出错啦！";
        } else if (exception instanceof BaseException) {
            message = "业务处理发生错误";
        }

        return BaseResponse.error(message, exception.getMessage());
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("全局异常注入正常");
    }
}
