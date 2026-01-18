package com.example.jwtsecurity.dto;

import java.io.Serializable;

//@SuppressWarnings("serial")
public class AuthRequest implements Serializable {

    private String username;
    private String password;

    public AuthRequest() {
    }

    public String getUsername() {
    	System.out.println(username);
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
    	System.out.println(password);
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
