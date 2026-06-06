package com.example.todoapi.exception; 

import com.example.todoapi.dto.ErrorResponse; 
import org.springframework.http.HttpStatus; 
import org.springframework.http.ResponseEntity; 
import org.springframework.validation.FieldError; 
import org.springframework.web.bind.MethodArgumentNotValidException; 
import org.springframework.web.bind.annotation.ExceptionHandler; 
import org.springframework.web.bind.annotation.RestControllerAdvice; 

import java.util.stream.Collectors; 

@RestControllerAdvice
public class GlobalExceptionHandler{
    

    //This method handles BadRequestExceptioin
    //Example: email is already 
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> handleBadRequest(BadRequestException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(ex.getMessage())); 
    }

    //Unauthorized Exception Errors
    //Example Missing Token, invalud token, wrong email / password
    @ExceptionHandler(UnauthorizedException.class)
    public ResposneEntity<ErrorResposne> handleUnauthorized(UnathorizedException ex){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorResponse(ex.getMessage())); 
    }

    //ForbiddenException Errors
    //Example: User tries to update/delete another users todo
    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<ErrorResposne> handleForbidden(ForbiddenException ex){
        return ResponseEntitty.status(HttpStatus.FORBIDDEN).body(new ErrorResposne(ex.getMessage)); 
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEnitty<ErrorResponse> handleNotFound(NotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOTFOUND).body(new ErrorResponse(ex.getMessage())); 
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntitiy<ErrorResponse> handleValidation(MethodArgumentValidException ex){
        
        String message = ex.getBindingResult().getFieldErrors().stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(",")); 
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(message)); 
    }   
}