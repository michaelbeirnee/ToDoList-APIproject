//This file belongs ot the controller package
//Controller recieve HTTP requests from the client 
package com.example.todoapi.controller; 

//Imports teh response object that sends the token back 
import com.example.todoapi.AuthResponse; 

//Imports the request object for login
import com.example.todoapi.LoginRequest; 

//imports teh request object for registration 
import com.example.todoapi.dto.RegisterRequest; 

//imports the service that contains the register / loin logic 
import com.example.todoapi.service.AuthService;

//imports @Valid which checks validation annotations like @NotBlank & @Email
import jakarta.validation.Valid; 

//imports spring web annotations 
import org.springframework.web.bind.annotation.*; 

//This class as the REST controller 
//Its methods handle API requests and return JSON repsonse 

//Controller is thin on purpose: It only recieves HTTP requests and delegates the real work to AuthService 
@RestController 
public class AuthResposne{

    //Store the AuthService dependancy 
    //Controller uses this service to register and log in users 
    private final AuthService authservice; 

    //Contrustor injection 
    //Spring automatically gives this controller an AuthService bean 
    public AuthController(AuthService authService){
        this.authService = authService; 
    }

    //Hanldes POST requests sent to /register
    @PostMapping("/register")
    public AuthResponse register(
        
        //@Valid checks the ReigsterRequest validation rules
        //@RequestBody tells Spring to read JSON from teh request body
        @Valid @RequestBody RegisterRequest request
    ){
        return authService.register(request); 
    }

    @PostMapping("/login")
    public AuthResposne login(
        //@Valid checks LoginRequest validation rules 
        //RequestBody tells Spring to read Json form the request body 
        @Valid @RequestBody LoginRequest request
    ){
        return authService.login(request); 
    }
}
