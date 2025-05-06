package com.matheus.HelpDesk.Resources.execption;

import java.io.Serializable;
import java.time.LocalDateTime;

public class StandardError implements Serializable {
    private static final long serialVersionUID = 1L;


    private LocalDateTime timestemp;
    private String error;
    private String message;
    private String path;
    private Integer status;

    public StandardError(){
        super();
    }
    public StandardError(LocalDateTime timestemp,  Integer status, String error, String message, String path) {
        this.timestemp = LocalDateTime.now();
        this.error = error;
        this.message = message;
        this.path = path;
        this.status = status;

    }

    public LocalDateTime getTimestemp() {
        return timestemp;
    }

    public void setTimestemp(LocalDateTime timestemp) {
        this.timestemp = timestemp;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
