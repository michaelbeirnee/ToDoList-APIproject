//This says the User class belongs inside the model package
//Models represent database tables / main data objects 
package com.example.todoapi.model; 

//Imports all Jakarta Persistence annotations like @Entity, @ID @Table, @Column
//These are used to connect Java classes to database tables 
import jakarta.persistence.*; 
import java.time.LocalDateTime; 

@Entity
@Table (name = "user")
public class User{
    
    @ID
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 
    @Column(nullable = false)
    private String name; 
    @Column(nullable = false, unique = true)
    private String email; 
    @Column(nullable = false)
    private String passwordHash; 
    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    public long getID(){
        return id; 
    }
    private void setId(){
        this.id = id; 
    }
    public String getEmail(){
        return email; 
    }
    private void setEmail(){
        this.email = email; 
    }
    public String getPassword(){
        return passwordHash; 
    }
    private void setPassword(){
        this.passwordHash = passwordHash; 
    }
    public LocalDateTime getTime(){
        return createdAt; 
    }
}