package com.service.JSON;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;

import java.io.IOException;

public class JSON<T> {
    private T obj;
    private Class<T> c;
    private String json_;

    public JSON(T o){
        this.obj = o;
    }

    public JSON(String s, Class<T> c){
        this.json_ = s;
        this.c = c;
    }


    public String toJSON() throws IOException {
        ObjectMapper om = new ObjectMapper();
        this.json_ = om.writeValueAsString(this.obj);
        return this.json_;
    }

    public T fromJSON() throws JsonProcessingException {
        ObjectMapper ow = new ObjectMapper();
        this.obj = ow.readValue(this.json_,this.c);
        return this.obj;
    }
}
