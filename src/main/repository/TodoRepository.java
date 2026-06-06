// class & interface talks to the database
package com.example.todoapi.repository; 

import com.example.todoapi.model.Todo; 
import org.springframework.data.domain.Page; 
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository; 

public interface TodoRepository extends JpaRepository<Todo, Long>{
    Page<Todo> findByUserId(Long userId, Pageable pageable); 
    Page<Todo> findByUserIdAndCompleted(Long userId, boolean completed, Pageable pageable); 

    Page<Todo> findByUserIdAndTitleContainingIgnoreCase(Long userId, String title, Pageable pageable);
    
    Page<Todo> findByUserIdAndTitleContainingIgnoreCase(
        Long userId, 
        String title, 
        Pageable pageable
    ); 

    Page<Todo> findByUserIdAndCompletedAndTitleCOntainingIgnoreCase(
        Long userid; 
        String title;
        Pageable pageable; 
        boolean completed; 
    )
}