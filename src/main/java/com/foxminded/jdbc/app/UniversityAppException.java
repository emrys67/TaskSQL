package com.foxminded.jdbc.app;

public class UniversityAppException extends RuntimeException{
    public UniversityAppException() {
    }

    public UniversityAppException(String message) {
        super(message);
    }

    public UniversityAppException(String message, Throwable cause) {
        super(message, cause);
    }

    public UniversityAppException(Throwable cause) {
        super(cause);
    }

    public UniversityAppException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
