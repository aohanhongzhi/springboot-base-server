package hxy.base.server.extend;

import hxy.base.server.entity.BaseResponse;
import hxy.base.server.entity.exception.BaseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ValidationException;

/**
 * @author eric
 * @program base-server
 * @description
 * @date 2021/11/2
 */
@RestControllerAdvice
public class GlobalExceptionHandler implements InitializingBean {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public BaseResponse clientException(HttpServletRequest request, MethodArgumentNotValidException exception) {
        // 这里的HttpStatus应该是400
        String message = exception.getBindingResult().getFieldError().getDefaultMessage();
        log.warn("{} 参数校验错误 Exception Message: {}", request.getRequestURI(), message, exception);

        return BaseResponse.error(message);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = Exception.class)
    public BaseResponse exceptionHandler(HttpServletRequest request, Exception exception) {
        boolean errorLevel = true;
        String message = exception.getMessage();

        if (exception instanceof NullPointerException) {
            message = "biu，踩雷啦！";
        } else if (exception instanceof ValidationException) {
            errorLevel = false;
            message = "参数检验出错啦！";
        } else if (exception instanceof BaseException) {
            message = "业务处理发生错误";
        }
        if (errorLevel) {
            log.error("{} Exception Message: {}", request.getRequestURI(), message, exception);
        } else {
            log.warn("{} Exception Message: {}", request.getRequestURI(), message, exception);
        }

        return BaseResponse.error(message, exception.getMessage());
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("全局异常注入正常");
    }
}
