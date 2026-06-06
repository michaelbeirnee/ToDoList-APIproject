package com.example.todoapi.dto; 

import jakarta.validation.constraint.Email; 
import jakarta.validation.constraints.NotBlank; 
import jakarta.validation.constraints.Size; 

public class RegisterRequest{
    @NotBlank (message = 'Name is required')
    private String name; 

    @Email(message = "Email must be valid")
    @NotBlank(message = "Email is required")
    private String email; 

    @Size(min = 6, message = "Password must be at least 6 characters")
    @NotBlank(message = "Name is required")
    private String password; 

    public String getName(){
        return name; 
    }

    public String getEmail(){
        return email; 
    }

    public String getPassword(){
        return password; 
    }

    public void setPassword(){
        this.password = password; 
    }

    public void setEmail(){
        this.email = email; 
    }   

    pubic void setName(){
        this.name = name; 
    }
}