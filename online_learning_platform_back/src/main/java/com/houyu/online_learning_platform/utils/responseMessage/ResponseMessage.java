package com.houyu.online_learning_platform.utils.responseMessage;

import lombok.Data;

@Data
public class ResponseMessage<T> {
    /**
     * 状态码
     */
    private Integer status;

    /**
     * 成功或失败结果返回："success" or "error"
     */
    private String message;

    /**
     * 响应内容
     */
    private T result;

    /**
     * 时间
     */
    private Long timestamp;

    public ResponseMessage() {
        this.timestamp = System.currentTimeMillis();
    }

    public ResponseMessage<T> status(int status_code) {
        this.setStatus(status_code);
        return this;
    }

    public ResponseMessage<T> message(String message) {
        this.setMessage(message);
        return this;
    }

    public ResponseMessage<T> result(T result) {
        this.setResult(result);
        return this;
    }


    /**
     * ResponseMessage.ok
     */
    // source
    public static <T> ResponseMessage<T> ok(T result, String message) {
        return new ResponseMessage<T>().status(Response.SUCCESS.getCode()).message(message).result(result);
    }

    public static <T> ResponseMessage<T> ok() {
        return ok(null);
    }

    public static <T> ResponseMessage<T> ok(T result) {
        return ok(result, "Success");
    }

    /**
     * ResponseMessage.error
     */
    // source
    public static <T> ResponseMessage<T> error(int status, String message) {
        return new ResponseMessage<T>().status(status).message(message).result(null);
    }

    public static <T> ResponseMessage<T> error() {
        return error(Response.INTERNAL_SERVER_ERROR.getCode(), "Default Error");
    }

    public static <T> ResponseMessage<T> error(String message) {
        return error(Response.INTERNAL_SERVER_ERROR.getCode(), message);
    }
}