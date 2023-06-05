package com.proyecto.test.dto;

public class ResponseReceived {

    private String status;
    private String message;
    private EmployeeReceived[] data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public EmployeeReceived[] getData() {
        return data;
    }

    public void setData(EmployeeReceived[] data) {
        this.data = data;
    }
}
