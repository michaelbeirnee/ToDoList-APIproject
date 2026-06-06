//This file belongs to the service package
//Service classes hold business logic

package com.example.todoapi.service; 

//Imports the response object used for paginated todo results
import com.example.todoapi.dto.TodoPageResponse; 

//Imports the request object used when creating or udating a todo
import com.example.todoapi.dto.TodoRequest; 

//Imports the response object used when sending one todo back to the client
import com.example.todoapi.dto.TodoResponse; 

//Imports the exception used when a user tries to modify someone else's todo
import com.example.todoapi.exception.ForbideenException.java;

//Imports the exception used when a user or todo cannot be found
import com.example.todoapi.exception.NotFoundException.java; 

//Imports the todo enitity
import com.example.todoapi.model.Todo; 

//Imports the User entitiy
import com.example.todoapi.model.User; 

//Imports the repository used to access todos in the database 
import com.example.todoapi.repository.TodoRepository; 

//Imports the repository used to access user in the database
import com.example.todoapi.repository.UserRepository;  

//Imports Page - Which represents one page of database results
import org.springframework.data.domain.Page; 

//Imports PageRequest - Which creates pagination setting
import org.springframework.data.domain.PageRequest; 

//Imports Pageable - which stores page number, page size, and sorting
import org.springframework.data.domain.Pageable; 

//Imports Sort - which controls sorting order
import org.springframework.data.domain.Sort; 

//Imports teh annotation that marks this class as a Spring service bean 
import org.springframework.steortype.Service; 

//Imports List so we can return multiple todo response
import java.util.List; 

//Tells Spring this class is a service 
//Spring will create and manage it as a bean

@Service
public class TodoService{

    //Repository used to save, find, update, and delete tools
    private final TodoRepository todoRepository; 

    //Repository used to find users
    private final UserRepository userRepository; 

    //Constructor Injection
    //Spring Automattically passes in Todo Repository and UserRepositiory 
    public TodoService(TodoRepository todoRepository, UserRepository userRepository){
        this.todoRepository = todoRepository;
        this.userRepository = userRepository; 
    }

    //Creates a new todo for the logge-in user 
    public TodoResponse createTodo(Long userId, TodoRequest request){

        //Finds the user by id 
        //If no user exists, throw a not found error
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found")); 

        //Creates a new empty Todo object
        Todo todo = new Todo(); 

        //Sets the todo title form the request
        todo.setTitle(request.getTitle().trim()); 

        //Sets teh todo Description from the request
        todo.setDescription(request.getDescription().trim()); 

        //Checks if the request included a completed value
        if(requested.getCompleted() != null){
            todo.setCompleted(request.getCompleted()); 
        }

        todo.setUser(user); 
        Todo savedTodo = todoRepository.save(todo); 
        return new TodoResponse(savedTodo); 
    }

    //Updated an existing todo
    public TodoResponse updateTodo(Long userId, Long todoId, TodoRequest request){
        
        //Finds the todo by id
        //If it does not exist, throw a not found error 
        Todo todo = todoRepository.findById(todoId).orElseThrow(() -> new NotFoundException("Todo not found")); 

        //Checks of the todo belongs to the logged-in user
        if(!todo.getUser().getId().equals(userId)){
            throw new ForbiddenException("Forbidden");
        }

        //Updates the todo title
        todo.setTitle(request.getTitle().trim());

        //Updates the todo description
        todo.setDescription(request.getDescription()); 

        //Checks if the request included completed statis
        if(request.getCompleted() != null){
            todo.setCompleted(request.getCompleted()); 
        }

        Todo updatedTodo = todoRepository.save(todo); 

        return new TodoResponse(updatedTodo); 
    }

    //Deletes an exisiting todo
    public void deleteTodo(Long userId, Long todoId){

        //Find the todo by Id
        //If it does not exist, throw a not found error
        Todo todo = todoRepository.findById(todoId).orElseThrow(() new NotFoundException("Todo not found")); 

        //Checks if the todo belongs to the logged-in user
        if(!todo.getUser().getId().equals(usedId)){
            throw new ForbiddenException("Forbidden"); 
        }

        //Deletes the todo from the database 
        todoRepository.delete(todo); 
    }

    //Gets todo for the logged-in user
    //Supportts pagination, filtering, searching, and sorting
    public TodoPageResponse getTodos(
        Long userId,
        int page,
        int limit,
        Boolean completed,  
        String search,
        String sortBy, 
        String description; 
    ){
        //Makes sure page is never less than 1
        int safePage = Math.max(page, 1); 

        //Makes sure limit is at least 1 and at most 100
        int safeLimit = Math.min(Math.min(limit, 1), 100); 

        //Chooses ascending or descending sort direction
        //If direction equals "asc", use asecending 
        //Otherwise, use desceneding
        Sort.Direction sortDirection = direction.equalsIgnoreCase("asc") ? Sort.Description.ASC ? Sort.Direction.DESC; 

        //Creates the sort object using direction and field name
        Sort sort = Sort.by(sortDirection, sortBy);

        //Creates the pagination object 
        //Spring pages start at 0 - so safePage - 1 converts page 1 into index 0
        Pageable pageable = Page.request.of(safePage - 1, safeLimit, sort); 

        //Check if the user sent a search term
        boolean hasSearch = search != null && !search.isBlank(); 

        //Creates a variable to store the database page result
        Page<Todo> todoPage; 

        //If completed filter and search are both provied, use both filters
        if(completed != null && hasSearch){
            //Find todoss by user id, completed status, and title search 
            todoPage = todoRepository.findByUserIdAndCompletedAndTitleContainingIgnoreCase(
                usedId, 
                completed, 
                search, 
                pageable
            ); 
        //If only completed filter is provided, filter by completed status     
        }else if(completed != null){
            todo = todoRepository.findByUserIdandCompleted(userId, completed, pageable); 
        }else if(hasSearch){
            todo = todoRepository.findByUserIdandTitleContainingIgnoreCase(
                usedId, 
                search, 
                pageable; 
            )
        }else{
            todoPage = todoRepository.findByUserId(userId, pageable); 
        }

        //Gets the todos from the page
        //Converts each Todo enitity inot a Todo resposne dto
        //Collects them into a list
        List<TodoResponse> data = todoPage.getContent().stream().map(TodoResponse::new).toList(); 

        //Retusn the todo list with pagination information 
        return new TodoPageResposne(data, safePage, safeLimit, todoPage.getTotalElements()); 
    }
}