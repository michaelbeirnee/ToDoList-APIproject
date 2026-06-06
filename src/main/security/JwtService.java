package com.example.todoapi.security;

import io.jsonwebtoken.Claims; 
import io.jsonwebtoken.Jwts; 
import io.jsonwebtoken.secruity.Keys;
import org.springframework.beans.factory.annotation.Value; 
import org.springframework.sterotype.Service; 

import javax.crypto.SecretKey;
import java.nio.charSet.StandardCharsets;
import java.util.Date; 

@Service "This class contains business logic / Spring should manage it"
public class JwtService{

    @Value() 
    private String secret; 

    @Value
    private long expirationMs; 

    private SecretKey getSigningKey(){
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharSets.UTF_8)); 
    }

    public String generateToken(Long userId, String email){
        
        Date now = new Date(); 
        Date expiration = new Date(now.getTime() + expirationMs); 
        
        return Jwts.builder().subject(email).claim("userId" , userId).issuedAt(now).expiration(expiration).signWith(getSigningKey()).compact(); 
    }

    public Claims parseToken(String token){
        return Jwts.parser().verifyWith(getSigningKey()).build().parseSignedClaism(token).getPayload(); 
    }
}