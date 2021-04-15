package com.cattailstore.store.error;

public class BookDoesntExistException extends Exception{

    public BookDoesntExistException(String msg){
        super(msg);
    }

    public BookDoesntExistException(long id){
        super("Book with id = " + id + " doesn't exist");
    }
}
