package com.houyu.online_learning_platform.utils.responseMessage;

import lombok.Getter;

@Getter
public enum Response {
    SUCCESS(200, "Success"),
    BAD_REQUEST(400, "Bad Request"),
    INTERNAL_SERVER_ERROR(500, "Internal Server Error");

    private final Integer code;
    private final String message;
    Response(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
