package com.matheus.HelpDesk.Resources.execption;

public class ObjNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;



    public ObjNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ObjNotFoundException(String message) {
        super(message);
    }
}
