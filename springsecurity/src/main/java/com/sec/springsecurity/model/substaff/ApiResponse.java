package com.sec.springsecurity.model.substaff;

public class ApiResponse<T> {
    private boolean success;
    private String message;
    private T body;

    public ApiResponse(boolean success, String message, T body) {
        this.success = success;
        this.message = message;
        this.body = body;
    }
    public ApiResponse() {}

    // Getters and Setters
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }
}
