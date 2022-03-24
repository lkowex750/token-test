package com.example.tokentest.security.jwt;

import com.example.tokentest.implement.UserPrinciple;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

@Component
public class JwtProvider {
    private static final Logger logger = LoggerFactory.getLogger(JwtProvider.class);

    @Value("${token.app.jwtSecret}")
    private String secret;

    public String generateJwtToken(Authentication authentication, String token) {
        UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();

        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE,1);

        if (token.equalsIgnoreCase("access_token")) {
            return Jwts.builder().setSubject((userPrinciple.getUsername()))
                    .setIssuedAt(new Date()).setExpiration(calendar.getTime())
                    .signWith(SignatureAlgorithm.HS512, secret).compact();
        } else {
            Calendar calendarRefresh = Calendar.getInstance();
            calendarRefresh.setTime(date);
            calendarRefresh.add(Calendar.MONTH,1);
            return Jwts.builder().setSubject((userPrinciple.getUsername()))
                    .setIssuedAt(new Date()).setExpiration(calendarRefresh.getTime())
                    .signWith(SignatureAlgorithm.HS512, secret).compact();
        }

    }

    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody().getSubject();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            logger.error("Invalid JWT signature -> Message: {} ", e.getMessage());
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token -> Message: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("Expired JWT token -> Message: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("Unsupported JWT token -> Message: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty -> Message: {}", e.getMessage());
        }
        return false;
    }
}
