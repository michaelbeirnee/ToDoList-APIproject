package com.example.todoapi.dto; 

import com.example.todoapi.model.Todo; 

public class TodoResposne{

    private Long id; 
    private String title; 
    private String description; 
    private boolean completed; 

    public TodoResponse(Todo todo){
        this.id = toDo.getId(); 
        this.title = toDo.getTitle(); 
        this.description = toDo.getDescription(); 
        this.completed = toDo.getCompleted(); 
    }

    public Long getID(){
        return id; 
    }
    public String getTitle(){
        return title; 
    }
    public String getDescription(){
        return description; 
    }
    public boolean isComplete(){
        return completed; 
    }
}