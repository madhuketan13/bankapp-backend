package com.dnb.apigatewayservice.utils;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;


@Component
public class JwtUtils {
	
	private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(JwtUtils.class);
	
	 @Value("${jwt.secret}")
	    private String jwtSecret;
	 

	 public Claims getClaims(final String token) {
		 // to parse the token using our secret key : 
		 try {
	            Claims body = Jwts.parser().setSigningKey(jwtSecret)
	                    .parseClaimsJws(token).getBody();
	            return body;
	        } catch (Exception e) {
	            System.out.println(e.getMessage() + " => " + e);
	        }
	        return null;
	 }
	 
	 public boolean validateJwtToken(String authToken) {
		 // to validate the token ?
		 try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
		} catch (SignatureException e) {
            LOGGER.error("JwtUtils | validateJwtToken | Invalid JWT signature: {}", e.getMessage());
            throw new SignatureException(e.getMessage());
        } catch (MalformedJwtException e) {
            LOGGER.error("JwtUtils | validateJwtToken | Invalid JWT token: {}", e.getMessage());
            throw new MalformedJwtException(e.getMessage());
        } catch (ExpiredJwtException e) {
            LOGGER.error("JwtUtils | validateJwtToken | JWT token is expired: {}", e.getMessage());
            throw new ExpiredJwtException(null,null,e.getMessage());
        } catch (UnsupportedJwtException e) {
            LOGGER.error("JwtUtils | validateJwtToken | JWT token is unsupported: {}", e.getMessage());
            throw new UnsupportedJwtException(e.getMessage());
        } catch (IllegalArgumentException e) {
            LOGGER.error("JwtUtils | validateJwtToken | JWT claims string is empty: {}", e.getMessage());
            throw new IllegalArgumentException(e.getMessage());
        }

		 
		 return false;
		
	}
}
