package com.example.todoapi.dto; 

public class AuthResposne{

    private String token; 

    public AuthResponse(String token){
        this.token = token; 
    }

    public String getToken(){
        return token; 
    }
}