package com.example.webflux.model;

public class UserRequestModel {

    private String username;
    private String password;

    public UserRequestModel() {
        
    }
    
    public UserRequestModel(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

}
