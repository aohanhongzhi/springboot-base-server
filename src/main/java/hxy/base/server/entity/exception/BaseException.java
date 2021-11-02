package hxy.base.server.entity.exception;

/**
 * @author eric
 * @program inspector-server
 * @description 业务异常
 * @date 2021/11/2
 */
public class BaseException extends RuntimeException {
    private String message;
    private int code = 0;

    public BaseException() {
        super();
    }

    public BaseException(String message) {
        super(message);
        this.message = message;
    }

    public BaseException(int code, String message) {
        super(message);
        this.code = code;

    }

    public BaseException(int code, Throwable throwable) {
        super(throwable);
        this.code = code;
    }

    public BaseException(String message, Throwable throwable) {
        super(throwable);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public int getCode() {
        return this.code;
    }
}
