//This file belongs to the exception package 
//Exception classes are used when something goes wrong in the applicaiton 
package com.example.todoapi.exception; 

//It extends RuntimeException which means it is an error that can happen
//Example : trying to register with an email that already exists 

public class BadRequestException extends RuntimeException{
    
    public BadRequestException(String message){
        //super(message) sends the message to the parent RuntimeException class 
        super(message); 
    }
}