package com.example.todoapi.dto; 

import jakarta.validation.constraints.NotBlank; 
//check request fields like NotBlank and Email

public Class TodoRequest{

    @NotBlank(message = "Title is requried")
    private String title; 

    private String description; 
    private Boolean completed; 

    //Getter method 
    public String getTitle(){
        return title; 
    }

    public String getDescription(){
        return description; 
    }

    public Boolean getCompleted(){
        return completed; 
    }

    //Setter method 
    private void setTitle(String title){
        this.title = title; 
    }
    private void setDescription(String description){
        this.description = description; 
    }
    private void setCompleted(Boolean completed){
        this.completed = completed; 
    }
}
