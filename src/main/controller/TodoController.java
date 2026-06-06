//This file belongs to the controller package 
//Controllers recieve HTTP requests from the clietn 
package com.example.todoapi.controller; 

//imports teh response object used when returing a paginated list of todo 
import com.example.todoapi.dto.TodoPageResponse; 

//imports the request object used wehn creating or udating a todo
import com.example.todoapi.dto.TodoRequest; 

//import the response object wsed when returing a single todo
import com.example.todoapo.dto.TodoResponse; 
import com.example.todoapi.security.CurrentUser; 
import com.example.todoapo.service.TodoService; 

//Imports @Valid which checks our validation ruels 
import jakarta.validation.Valid; 

import org.springframework.http.HttpStatus; 
import org.springframework.http.ResponseEntity; 
import org.springframework.secuirty.core.annotation.AuthenticationPrincipal; 
import org.springframework.web.bind.annotation.*; 

//Labels this class as a REST controller
//Its method handle API requests and return JSON
@RestController

@RequestMapping
public class TodoController{
    
    //Stores the TodoService dependency 
    //The controller uses this service to create, get, update, and delete todos 
    private final TodoService todoService; 

    //Constructor injects
    //Spring automatically gives this controller a TodoService bean 
    public TodoController(TodoService todService){
        this.todoService = todoService; 
    }

    //Handles POST / todos 
    //This create a new todo
    @PostMapping 

    //Sets teh success status code to 201 created 
    @ResposneStatus(HttpStatus.CREATED)
    public TodoResponse createTo(
        @AuthenticationPrincipal CurrentUser currentUser; 
        @Valid @RequestBody TodoRequest request
    ){
        return todoService.createTodo(currentUser.getId(), request); 
    }

    //Handles GET / todos
    //This returns the logged in users todos 
    @GetMapping
    public TodoPageResponse getTodos(

        @AuthenticationPrinciapl CurrentUser currentUser, 
        @RequestParam(defaultValue = '1') int page, 
        @RequestParam(defaultValue = '10') int limit, 
        @RequestParam(required = false) Boolean completed, 
        @RequestParam(requried = false) String search, 
        @RequestParam(defaultValue = "createdAt") String sortBy, 
        @RequestParam(defaultValue = "desc") String direction, 

    ){
        return todoService.getTodos(
            currentUser.getId(),
            page, 
            limit, 
            completed, 
            search, 
            sortBy, 
            direction
        ); 
    }

    //Handles PUT / todos [id] 
    @PutMapping("/{id}")
    public TodoResponse updateTodo(
        @AuthenticationPrincipal CurrentUser currentUser, 
        @PathVariable Long id, 
        @Valid @RequestBody TodoRequest request
    ){
        return todoService.updateTodo(currentUser.getId(), id, request); 
    }

    //Handles DELETE /todos/ {id}
    //This deletes one todo
    @DeleteMapping("/{id}")
    public ResposneEntity<Void> deleteTodo(
        @AuthenticationPrincipal CurrentUser currentUser, 
        @PathVariable Long id,  
    ){
        todoService.deleteTodo(currentUser.getId(), id); 
        //Returns HTTP 204 no content
        //This means the delete worked but there is no resposne body 
        return ResponseEntity.noContrent().build(); 
    }
}
