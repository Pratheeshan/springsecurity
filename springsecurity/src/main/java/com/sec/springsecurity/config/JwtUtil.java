package com.sec.springsecurity.config;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {
    private static final Logger log = LoggerFactory.getLogger(JwtUtil.class);

    //symmetric key generated using the HS256 (HMAC-SHA256) algorithm
    private static final SecretKey secret = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    //field holds the token's validity duration in milliseconds.
    @Value("${app.jwt.expiration}")
    private int expiration;

    //Extracts the username from the authenticated user,
    public String generate(Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", "ROLE_"+userDetails.getAccessLevel().name());


        return Jwts
                .builder() //Builds a JWT with
                .setClaims(claims)
                .setSubject(userDetails.getUsername()) //Subject: The username.
                .setIssuedAt(new Date()) //The current time.
                .setExpiration(new Date(new Date().getTime() + expiration))
                .signWith(secret, SignatureAlgorithm.HS256) //Signed with the secret key.
                .compact(); //A signed JWT string.
    }

    //Extracts the subject (username) from the token’s claims.
    public String getUsername(String token) {
        return Jwts //username embedded in the token.
                .parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    //Extracts the JWT from the Authorization header of the request.
    public String parse(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");

        if (StringUtils.hasText(authorization) && authorization.startsWith("Bearer ")) {//JWT Token if it starts with "Bearer "; otherwise, null.
            return authorization.substring(7);
        }

        return null;
    }

    public boolean validate(String authToken) {
        try {
            //Validates the JWT by parsing it with the secret key.
            Jwts.parser().setSigningKey(secret).parseClaimsJws(authToken);
            return true;
            //Handles specific exceptions to log reasons for invalid tokens
        } catch (SignatureException e) {
            log.error("Invalid JWT signature, cause: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            log.error("Invalid JWT token, cause: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            log.error("JWT token is expired, cause: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.error("JWT token is unsupported, cause: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty, cause: {}", e.getMessage());
        }

        return false;
    }
}
