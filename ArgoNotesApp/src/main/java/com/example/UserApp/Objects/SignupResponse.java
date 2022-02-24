package com.example.UserApp.Objects;

public class SignupResponse {
    private int response_code;
    private String response_def;
    private String auth_id;

    public SignupResponse(){}

    public SignupResponse(int response_code, String response_def, String auth_id){
        this.response_code = response_code;
        this.response_def = response_def;
        this.auth_id = auth_id;
    }

    public int getResponse_code() {
        return response_code;
    }

    public void setResponse_code(int response_code) {
        this.response_code = response_code;
    }

    public String getResponse_def() {
        return response_def;
    }

    public void setResponse_def(String response_def) {
        this.response_def = response_def;
    }

    public String getAuth_id() {
        return auth_id;
    }

    public void setAuth_id(String auth_id) {
        this.auth_id = auth_id;
    }
}
