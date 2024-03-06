package com.example.orderservice.security;


import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {
    @Value(value="${app.jwt-secret")
    private String jwtSecret;

    private Key key(){
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    public String getUsername(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key())
                .build()
                .parseClaimsJws(token)
                .getBody();
        String userId = (String) claims.get("userId");
        return userId;
    }
    public boolean validateToken(String token){
        try {
            Claims claims=Jwts.parserBuilder().setSigningKey(key()).build().parseClaimsJws(token).getBody();
            Date expiryDate= (Date) claims.get("expiryAt");
            String mail=(String) claims.get("email");
            System.out.println("HELLO from validate! "+mail);
            return !(expiryDate.before(new Date()));
        }
        catch(MalformedJwtException e){
            throw new AuthApiException(HttpStatus.BAD_REQUEST,"Invalid token");
        }
        catch(ExpiredJwtException e){
            throw new AuthApiException(HttpStatus.BAD_REQUEST,"Token Expired");
        }
        catch(UnsupportedJwtException e){
            throw new AuthApiException(HttpStatus.BAD_REQUEST,"Unsupported Token");
        }
        catch(IllegalArgumentException e){
            throw new AuthApiException(HttpStatus.BAD_REQUEST,"Jwt claims string is empty");
        }
    }
}
