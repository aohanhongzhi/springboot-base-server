package hxy.base.server.entity;



import hxy.base.server.entity.enums.BaseStatusCodeEnum;
import hxy.base.server.util.I18nMessage;

import java.io.Serializable;

/**
 * @author eric
 * @program data
 * @description 基础返回值
 * @date 2021/10/12
 */
public class BaseResponse<T> implements Serializable {
    private String code;
    private String message;
    private T data;
    private long timestamp = System.currentTimeMillis();

    public BaseResponse(String message) {
        this.message = message;
    }

    public BaseResponse(BaseStatusCodeEnum baseStatusCodeEnum) {
        this.message = baseStatusCodeEnum.description();
        this.code = baseStatusCodeEnum.code();
    }

    public BaseResponse(BaseStatusCodeEnum baseStatusCodeEnum, T data) {
        this.message = baseStatusCodeEnum.description();
        this.code = baseStatusCodeEnum.code();
        this.data = data;
    }

    public BaseResponse(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }


    public static <T> BaseResponse<T> success() {
        return new BaseResponse<T>(BaseStatusCodeEnum.SUCCESS);
    }

    public static <T> BaseResponse<T> success(String message) {
        message = I18nMessage.getMessage(message);
        return new BaseResponse<T>(BaseStatusCodeEnum.SUCCESS.code(), message, null);
    }

    public static <T> BaseResponse<T> success(T data) {
        return new BaseResponse<T>(BaseStatusCodeEnum.SUCCESS, data);
    }

    public static <T> BaseResponse<T> success(String message, T data) {
        message = I18nMessage.getMessage(message);
        return new BaseResponse<T>(BaseStatusCodeEnum.SUCCESS.code(), message, data);
    }

    public static <T> BaseResponse<T> error() {
        return new BaseResponse<T>(BaseStatusCodeEnum.ERROR);
    }

    public static <T> BaseResponse<T> error(String message) {
        message = I18nMessage.getMessage(message);
        return new BaseResponse<T>(BaseStatusCodeEnum.ERROR.code(), message, null);
    }

    public static <T> BaseResponse<T> error(T data) {
        return new BaseResponse<T>(BaseStatusCodeEnum.ERROR, data);
    }

    public static <T> BaseResponse<T> error(String message, T data) {
        message = I18nMessage.getMessage(message);
        return new BaseResponse<T>(BaseStatusCodeEnum.ERROR.code(), message, data);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
