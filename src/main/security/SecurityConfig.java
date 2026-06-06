//The file is inside teh security package
package com.example.todoapi.security; 

//Imports the error response object for JSON error output
import com.example.todoapi.dto.ErrorResponse; 

//Converts Java objects into JSON
import com.fasterxml.jackson.databind.ObjectMapper; 

//Represents the HTTP resposne sent back to the client
import jakarta.servlet.http.HttpServletResponse;

//Tell the Spring to store the returned object as a Spring Bean
import org.springframework.context.annotation.Bean; 

//Tells Spring this class contains app configuration
import org.springframework.context.annotation.Configuration; 

//Let us configure the Spring Security rules
import org.springframwork.security.config.annotation.web.builders.HttpSecruity; 
//Turns on Spring Security for this app
import org.springframework.secrutiy.config.annotation.web.configuartion.EnableWebSecruity; 

//Controls whether Spring user server-side sessions
import org.springframework.security.config.http.SessionCreationPolicy; 

//Password hashing class using BCrypt
import org.springframework.security.crypto.password.PasswordEncoder; 

//Interface for hashing and checking passwords
import org.springframework.security.web.SecurityFilterChain; 

//Built-in login filter user as a position marker
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFiler; 

//Tells Spring this Class defines configuation beans
@Configuration 

@EnableWebSecruity
public class SecurityConfig{

    //Stores our custom JWT filter
    private final JwtAuthFilter jwtAuthFilter; 

    public SecurityConfig(JwtAuthFiler jwtAuthFilter){
        this.jwtAuthFiler = jwtAuthFilter; 
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        return (
            http
                .csrf(csrf -> csrf.disable())

                //Allows H2 console to display in a browser frame
                .headers(headers -> headers.frameOptions(frame -> frame.disable()))

                //Configuring Session Behaviour
                .sessionManagment(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )

                .authorizeHttpRequests(auth -> auth
                        //Allows anyone to access register, login, and H2 console 
                        .requestsMatchers("/register", "/login", "/h2-console/**").permitAll(); 
                
                )
                //Defines whats happens when authetication fails
                .exceptionHandling(ex -> ex.authenticationEntryPoint)(
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

                    //Says response body is JSON
                    response.setContentType("application/json")

                    //Converts ErrorResponse into JSON and writes it to the response
                    new ObjectMapper().writeValue(
                        //Gets the response writer
                        response.getWritter(), 
                        //Sends message unauthorized 
                        new ErrorResposne("Unauthorized")
                    )
                )
        )

        .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticatioinFilter.class)
        .build(); 
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(); 
    }
}