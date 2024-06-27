package com.example.ShoppingManagementSystem.config;

import java.util.Date;
import java.util.function.Function;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.stereotype.Component;

@Component
public class JwtUtil {
	
	private String secret = "mysecret";
	
	public String extractUsername(String token) {
		return extractClaim(token,Claims::getSubject);
	}
	
	public Date extractExpiration(String token) {
		return extractClaim(token,Claims::getExpiration);
	}
	
	public <T> T extractClaim(String token, Function<Claims,T> claimsResolver) {
		final Claims claim = extractAllClaims(token);
		return claimsResolver.apply(claim);
	}
	
	public Claims extractAllClaims(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}
	
	public Boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}
	
	public String generateToken(String username) {
		return createToken(username);
	}

	public String createToken(String username) {
		return Jwts.builder()
				.setSubject(username)
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis()+ 1000*60*60*10))
				.signWith(SignatureAlgorithm.HS256, secret)
				.compact();
	}
	
	public Boolean validateToken(String token, String username) {
		final String extractedUsername = extractUsername(token);
		return extractedUsername.equals(username) && !isTokenExpired(token);
	}
}
