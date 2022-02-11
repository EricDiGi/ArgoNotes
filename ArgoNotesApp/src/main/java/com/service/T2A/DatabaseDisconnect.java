package com.service.T2A;

public class DatabaseDisconnect extends Exception{
    public DatabaseDisconnect(){
        super();
    }

    public DatabaseDisconnect(String msg){
        super(msg);
    }
}
