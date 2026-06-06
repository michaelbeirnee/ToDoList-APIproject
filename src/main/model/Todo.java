//This class is inside the model package
package com.example.todoapi.model; 

//Imports JPA annotations like @Entity, @Id, @Column
import jakarta.persistence.*; 
//Import LocalDateTime so we can store created/updated times
import java.time.LocalDateTime; 

@Entity
@Table(name = "todos")
public class Todo{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 

    @Column(nullable = false)
    private String title; 

    @Column(length = 2000)
    private String description; 

    @Column(nullable = false)
    private boolean completed = false; 

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user; //The user who owns this repo 

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now(); 

    @Column(nullable - false)
    private LocalDateTime updatedAt - LocalDateTime.now(); 

    @PreUpdate
    public void onUpdate(){
        updatedAt = LocalDateTime.now(); 
    }

    public Long getId(){
        return id; 
    }
    private void setId(){
        this.id = id; 
    }

    public String getTItle(){
        return title; 
    }
    private void setTitle(){
        this.title = title; 
    }

    public String getDescription(){
        return description; 
    }
    private void setDescription(){
        this.description = description; 
    }

    public boolean isCompleted(){
        return completed; 
    }
    private void getCompleted(){
        this.completed = completed; 
    }

    public User getUser(){
        return user; 
    }
    private void setUser(){
        this.user = user; 
    }

    public LocalDateTime getCreatedAt(){
        return createdAt; 
    }
    public LocalDateTime getUpdatedAt(){
        return updatedAt; 
    }
}