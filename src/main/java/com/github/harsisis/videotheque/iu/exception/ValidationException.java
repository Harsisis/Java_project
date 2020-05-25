package com.github.harsisis.videotheque.iu.exception;

public class ValidationException extends Exception {

    private String title;

    public ValidationException(String message, String title, Throwable cause) {
        super(message, cause);
        this.title = title;
    }


    public ValidationException(String message, String title) {
        super(message);
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
