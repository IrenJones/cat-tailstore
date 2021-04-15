package com.cattailstore.store.error;

public class Message {

    private final String fieldName;

    private final String message;

    public Message(String fieldName, String message) {
        this.fieldName = fieldName;
        this.message = message;
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getMessage() {
        return message;
    }
}
