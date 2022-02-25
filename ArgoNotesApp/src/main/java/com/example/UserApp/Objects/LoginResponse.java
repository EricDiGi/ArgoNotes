package com.example.UserApp.Objects;

public class LoginResponse {
    private String user;
    private String[] notes;

    public LoginResponse(){}

    public LoginResponse(String user, String[] notes){
        this.user = user;
        this.notes = notes;
    }

    public String[] getNotes() {
        return notes;
    }

    public void setNotes(String[] notes) {
        this.notes = notes;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
