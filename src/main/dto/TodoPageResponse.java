package com.example.todoapi.dto;

import java.util.List; 

public class TodoPageResponse{
    
    private List<TodoResponse> data; 
    private int page; 
    private int limit; 
    private long total; 

    public TodoPageResponse(List<TodoResponse> data, int page, int limit, long total){
        this.data = data; 
        this.page = page; 
        this.limit = limit; 
        this.total = total; 
    }

    //Get Methods 
    public List<TodoResponse> getData(){
        return data; 
    }
    public int getLimit(){
        return limit; 
    }
    public int getPage(){
        return page; 
    }
    public long getTotal(){
        return total; 
    }
 
}