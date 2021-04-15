package com.cattailstore.store.error;

import java.util.ArrayList;
import java.util.List;

public class ErrorResponse {

    private List<Message> msgs = new ArrayList<>();

    public List<Message> getMessages() {
        return msgs;
    }

    public void setViolations(List<Message> ms) {
        this.msgs = new ArrayList<>(ms);
    }
}
