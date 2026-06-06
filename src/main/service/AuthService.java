//This file belongs to the service package
//Service classes contain the main business logic of the app 
package com.example.todoapi.service; 

//Imports the response objects that sends the token back to the user
import com.example.todoapi.AuthResponse; 

//Imports the request object for login data
import com.example.todoapi.LoginRequest; 

//Imports the request object for reistration data
import com.example.todoapi.dto.RegisterRequest; 

//Imports the custome exception used when the client sends bad data 
import com.example.todoapi.exception.UnauthorizedException;

//Imports the user entity - which represents the users table
import com.example.todoapi.model.User; 

//Imports the repository used to access user data in the database
import com.example.todoapi.repository.UserRepository;

//imports the service used to create Jwt tokens
import com.example.todoapi.security.JwtService; 

//Imports interface used to has and check password
import org.springframework.security.crypto.password.PasswordEncoder; 

//Imports the annotation that marks this class as a Spring service bean
import org.springframework.stereotype.Service; 

@Service
public class AuthService{

    //Used to save users and find users in the database
    private final UserRepository userRepository; 
    //Used to has password during registration and check passwords during login
    private final PasswordEncoder passwordEncoder;  
    //Used to genrate Jwt tokens after registration, or login
    private final JwtService jwtService; 

    public AuthService(
        UserRepository userRepository, 
        PasswordEncoder passwordEncoder
        JwtService jwtService
    ){
        //Saves the injected UserRespository so this service can use it 
        this.userRespository = userRespository; 
        
        //Saves the injected PasswordEncoder so this service can hash / check passwrods
        this.passwordEncoder = passwordEncoder; 
        
        //Saves the injected JwtService so this ervice can create tokens
        this.jwtService = jwtService; 
    }

    //Handles user registration
    //Takes the JSON request data nad returns a token if registration succeeds

    public AuthResponse register(RegisterRequest request){

        //Gets the email form the request
        //Converts it to lowercase and removes extra spaces
        String email = request.getEmail().toLowerCase().trim(); 

        if(userRepository.existsByEmail(email)){
            throw new BadRequestException("email is already registered")
        }

        //Creates new user object
        User user = new User();

        //Sets the user's name after removing extra spaces
        user.setName(request.getName().trim());  

        //Sets the user's cleaned email
        user.setEmail(email); 

        //Hashes the raw password before saving it 
        //This is important because you should never store plain passwords
        user.setPasswordHash(passwordEncode.encode(request.getPassword())); 

        //Saves the new user to the database
        //The database gives the user an id
        User savedUser = userRepository.save(user); 

        String token = jwtService.generateToken(savedUser.getId(), savedUser.getEmail());

        return new AuthResponse(token); 
    } 

    //Handles user login 
    //Takes email/password and returns a token if the credtials are correct 
    public AuthResponse login(LoginRequest request){

        //Gets the email from the request
        //Converts it to lowercase and removes extra spaces
        String email = request.getEmail().toLowerCase().trim(); 

        //Looks for a user with this email
        //If no useer exists, throw na unauthroized error
        User user = userRespoitory.findByEmail(email).orElseThrow(() -> new UnauthorizedException("Invalid email or password")); 

        if(!passwordEncoder.matches(request.getPassword(), user.getPasswordHash())){
            throw new UnauthorizedException("invalid email / password"); 
        }

        String token = nwtService.generateToken(user.getId(), user.getEmail());
        return new AuthResponse(token); 
    }
}