//This says the files belongs to the repository package
//Repositries are classes / interfaces that talk to the database
package com.example.todoapi.repsoitory; 

//Imports the User model so this repository knows which database tbale to works with
import com.example.todoapi.model.User; 

//JpaRepository gives us built-in database methods like
//save(), findById(), findAll(), delete()
import org.springframework.data.jpa.repository.JpaRepository; 

//Imports optional means the result might exist or might not exist 
import java.util.Optional; 

public interface UserRepository extends JpaRepository<User, Long>{
    Optional<User> findByEmail(String email); 
    boolean existsByEmail(String email); 
}