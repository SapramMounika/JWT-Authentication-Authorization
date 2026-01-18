package com.example.jwtsecurity.util;

import java.security.Key;
import java.sql.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.JwtParserBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
private String secretKey =  "mysecretkeymysecretkeymysecretkey123";

// 1 hour in milliseconds
private long expTime = 60*60*1000;

private Key getKey() {
	return Keys.hmacShaKeyFor(secretKey.getBytes());
}
//generate Token
public String generateToken(String username, String role ) {
	  JwtBuilder jwtBuilder = Jwts.builder();
	  jwtBuilder.setSubject(username);
	  jwtBuilder.claim("role", role);
	  Date issuedAt = new Date(System.currentTimeMillis());
	  jwtBuilder.setIssuedAt(issuedAt);
	  Date Expiration = new Date(System.currentTimeMillis() + expTime);
	  jwtBuilder.setExpiration(Expiration);
	  jwtBuilder.signWith(getKey(), SignatureAlgorithm.HS256);

	    // 7. Build final JWT string
	    String token = jwtBuilder.compact();

	    return token;
	    
}


public String extractUsername(String token) {
    Claims claims = getClaims(token);
    return claims.getSubject();
}


//------------------ Validate Token ------------------
public boolean validateToken(String token) {
    try {
        getClaims(token); // verifies signature + expiry
        return true;
    } catch (Exception e) {
        return false;
    }
}


private Claims getClaims(String token) {

    // 1. Create parser builder
    JwtParserBuilder parserBuilder = Jwts.parserBuilder();

    // 2. Set signing key
    parserBuilder.setSigningKey(getKey());

    // 3. Build the parser
    JwtParser jwtParser = parserBuilder.build();

    // 4. Parse the JWT token
    Jws<Claims> parsedToken = jwtParser.parseClaimsJws(token);

    // 5. Extract claims (payload)
    Claims claims = parsedToken.getBody();

    return claims;
}

}
