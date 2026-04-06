package com.app.quantityMeasurementApp.security;
import com.app.quantityMeasurementApp.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class AuthUtils {

    private final String SECRET_KEY = "bXktc2VjcmV0LWtleS1mb3Itand0LXRva2VuLTEyMzQ1Ng==";

    private SecretKey getKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET_KEY));
    }

    //Generate Token
    public String generateJwtToken(User user) {
        return Jwts.builder()
                .setSubject(user.getUsername())
                .claim("userId", user.getId())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 hour
                .signWith(getKey())
                .compact();
    }

    //Extract Username
    public String getUsernameFromToken(String token) {
        return extractAllClaims(token).getSubject();
    }

    //Extract All Claims (Reusable)
    public Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    //Check Expiration
    public boolean isTokenExpired(String token) {
        return extractAllClaims(token).getExpiration().before(new Date());
    }

    //Validate Token
    public boolean isTokenValid(String token, String username) {
        final String extractedUsername = getUsernameFromToken(token);
        return (extractedUsername.equals(username) && !isTokenExpired(token));
    }
}