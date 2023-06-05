package com.proyecto.test.business;

public class BusinessException extends RuntimeException {

    private int status = 0;
    private String message;
    private String url;

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, int code) {
        this.message = message;
        this.status = code;
        this.url = url;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public String getUrl() {
        return url;
    }
}
