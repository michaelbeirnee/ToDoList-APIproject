package com.exampe.todoapi.dto; 

import jakrata.validation.constratints.email; 
import jakrata.validation.constraints.NotBlank; 

public class LoginRequest{

    @Email (message = "Email must be valid")
    @NotBlank (message = "Email is required")
    private String email;

    @NotBlank (message = "Name is required")
    private String name;

    @NotBlank (message = "Password is required")
    private String password; 

    public String getEmail(){
        return this.email; 
    }

    public String getName(){
        return name; 
    }

    public String getPassword(){
        return this.password; 
    }
}