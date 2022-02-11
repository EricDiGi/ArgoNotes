package com.example.UserApp.Objects;

public class UnHashableException extends Throwable {
    public UnHashableException(){
        super();
    }
    public UnHashableException(String msg){
        super(msg);
    }
}
