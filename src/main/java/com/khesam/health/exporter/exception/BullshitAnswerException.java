package com.khesam.health.exporter.exception;

public class BullshitAnswerException extends RuntimeException {

    public BullshitAnswerException(String message) {
        super(message);
    }

    public BullshitAnswerException(String message, Throwable cause) {
        super(message, cause);
    }
}
