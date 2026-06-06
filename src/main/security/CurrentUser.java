package com.example.todoapi.security;

public class CurrentUser{
    private final Long id; 
    private final String email; 

    public CurrentUser(Long id, String email){
        this.id = id; 
        this.email = email; 
    }

    public Long getId(){
        return id; 
    }

    public String getEmail(){
        return email; 
    }
}