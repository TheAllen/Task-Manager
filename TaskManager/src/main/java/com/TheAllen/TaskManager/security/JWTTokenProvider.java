package com.TheAllen.TaskManager.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.TheAllen.TaskManager.domain.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JWTTokenProvider {
	
	public String generateToken(Authentication auth) {
		
		User user = (User)auth.getPrincipal();
		Date now = new Date(System.currentTimeMillis());
		
		Date expirationDate = new Date(now.getTime() + SecurityConstants.EXPIRATION_TIME);
		
		String userId = Long.toString(user.getId());
		
		Map<String, Object> claims = new HashMap<>();
		claims.put("id", (Long.toString(user.getId())));
		claims.put("username", user.getUsername());
		claims.put("name", user.getUsername());
		
		return Jwts.builder()
				.setSubject(userId)
				.setClaims(claims)
				.setIssuedAt(now)
				.signWith(SignatureAlgorithm.HS512, SecurityConstants.SECRET_KEY)
				.compact();
		
	}
	
	//Validate JWT
	public boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey(SecurityConstants.SECRET_KEY).parseClaimsJws(token);
			return true;
		}catch(SignatureException ex) {
			System.out.println("Invalid JWT Signature");
		}catch(MalformedJwtException ex) {
			System.out.println("Invalid JWT");
		}catch(ExpiredJwtException ex) {
			System.out.println("Expired JWT");
		}catch(UnsupportedJwtException ex) {
			System.out.println("Unsupported JWT");
		}catch(IllegalArgumentException ex) {
			System.out.println("JWT claims string is empty");
		}
		
		return false;
	}
	
	//Get user Id from token
	public Long getUserIdFromJWT(String token) {
		
		Claims claims = Jwts.parser().setSigningKey(SecurityConstants.SECRET_KEY).parseClaimsJws(token).getBody();
		Long id = Long.parseLong((String)claims.get("id"));
		
		return id;
	}
}
