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

    private String jwtSecret="e58b1787cf04010493fe722e9129e1e4c9876b91ddb67683dcf28d87d467cc1a";


    private Key key(){
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    public String getUsername(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key())
                .build()
                .parseClaimsJws(token)
                .getBody();
        String userId = ((Integer) claims.get("userId")).toString();
        System.out.println("USER ID: "+userId);
        return userId;
    }
    public boolean validateToken(String token){
        try {
            Claims claims=Jwts.parserBuilder().setSigningKey(key()).build().parseClaimsJws(token).getBody();
            System.out.println("HELLO from validate! ");
            System.out.println(claims.get("expiryAt"));
            Date expiryDate= new Date((Long) claims.get("expiryAt"));
            String mail=(String) claims.get("email");
            Date currentDate=new Date();
            System.out.println("HELLO from validate! "+currentDate);
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
