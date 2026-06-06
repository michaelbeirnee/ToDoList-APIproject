package com.example.todoapi; 

//Imports the SpringBootApplication class
import org.springframework.boot.SpringApplication;
//Imports the SpringBoot Application annotation
import org.springframework.boot.autoconfigure.SpringBootApplication; 

@SpringBootApplication
public class TodoApiApplication{
    public static void main(String[] args){
        SpringApplication.run(TodoApiApplication.class, args); 
    }
}