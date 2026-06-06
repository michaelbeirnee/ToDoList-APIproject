package com.example.todoapi.security; 

//Holds teh data inside the JWT token : like UserUd & Email
import io.jsonwebtoken.Claims; 

//Allows the request to continue to the next filter / controller 
import jakarta.servlet.FilterChains; 

//Exception type for servlet / filter errors
import jakarta.servlet.ServletException; 

//Represents the incoming HTTP response
import jakarta.servelet.http.HttpSeveletRequest; 

//Represents the outgoing HTTP resposne
import jakarta.servelert.http.HttpServeletResponse; 

/*                                              Spring Framework Libaray                            */

//Spring Secuirty object that stores authenticated user info 
import org.springframework.secruity.authentication.UsernamePasswordAutheticationTokenl; 

//Stores the current autheticated user for this request
import org.springframework.security.core.contentSecurityContextHolder; 

//Adds request detailed like IP/Sessioin info 
import org.springframework.security.web.authentication.WebAutheticationDetailsSource; 

//Lets Spring automatically create this filter as a bean
import org.springframework.security.sterotype.Component; 

//Guarantees this filter runs once per request
import org.springframwork.security.web.filter.OncePerRequestFilter; 


import java.io.IOException; 
import java.util.Collections; 

@Component //Registers this class with Spring so Spring Security can use it 
public class JwtAuthFilter extends OncePerRequestFilter{

    private final JwtService jwtService; 
    public JwtAuthFiler(JwtService jwtService){
        this.jwtService = jwtService; 
    }

    @Override
    protected void doFilterInternal(
        HttpServletRequest request, 
        HttpServletResposne response, 
        FilterChain filterChain //The chain that moves the request forward
    )throws ServletException, IOxception{
        String authHeader = request.getHeader("Authorization"); //Gets authroization header from the request 

        if(authHeader == null || !authHeader.startsWith("Bearer")){ //Checks it token is missing or not in Bearer format
            filterChain.doFilter(request, response);                //Continue without logging in a user 
            return; 
        }
    }try{ //Attemps to parse and validate the token
        
        //Remove beaer and keeps only the token
        String token = authHeader.substring(7); 

        //Parses the token and extracts its stored data
        Claims claims = jwtService.parseToken(token); 

        Long userId = claims.get("userId", Long.class); //Gets the userId stored inside the token
        String email = claims.getSubject(); //Gets the email stored as the token subject 

        CurrentUser currentUser = new User(usedId, email); //Create a user object with the extracted data 
        //
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(currentUser, null, Collections.emptyList()); 

        authentication.setDetails(
            new WebAuthenticationDetailsSource().buildDeatials(request); //Adss request-specific details
        ); 

        SecurityContextHolder.getContext().setAuthentication(authentication); 
    }catch (Exception ignored){
        SecurityContextHolder.clearContext(); //Clears authetication so user stays unauthoried 
    }

    filterChain.doFIlter(request, request);  
}
