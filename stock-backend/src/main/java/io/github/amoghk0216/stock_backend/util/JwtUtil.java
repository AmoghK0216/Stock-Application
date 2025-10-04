package io.github.amoghk0216.stock_backend.util;

import io.github.amoghk0216.stock_backend.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {

    String secret = "";

    public JwtUtil(){
        try{
            KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
            SecretKey sk = keyGen.generateKey();
            secret = sk.toString();
        }catch (NoSuchAlgorithmException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    public String generateToken(UserDetails userDetails){
        return Jwts.builder()
                .subject(userDetails.getUsername())
                .claims()
                .add(new HashMap<String, Object>())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 30*60*60*60))
                .and()
                .signWith(generateKey())
                .compact();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        return extractUsername(token).equals(userDetails.getUsername()) && !isExpired(token);
    }

    private boolean isExpired(String token) {
        return Jwts.parser()
                .verifyWith((SecretKey) generateKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getExpiration()
                .before(new Date());
    }

    public Key generateKey(){
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    public String extractUsername(String token) {
        return Jwts.parser()
                .verifyWith((SecretKey) generateKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }
}
