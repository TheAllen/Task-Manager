package com.TheAllen.TaskManager.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.TheAllen.TaskManager.domain.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

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
				.setIssuedAt(now)
				.signWith(SignatureAlgorithm.HS512, SecurityConstants.SECRET_KEY)
				.compact();
		
	}
}
