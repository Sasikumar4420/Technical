package com.pack.course_enrollment.config;

import java.security.Key;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.function.Function;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.pack.course_enrollment.entity.Trainee;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
	private final String SECRET_KEY="komal7035komal7036nadh234mylapuru111pavan8500venkat559kumar995123njjghyj67554gfgfxfg78767ghcff";
	private Claims extractAllClaims(String token) {
		return Jwts.parserBuilder()
				.setSigningKey(getSignInKey())
				.build()
				.parseClaimsJws(token)
				.getBody();
	}

	private Key getSignInKey() {
		byte[] keyBytes=Decoders.BASE64.decode(SECRET_KEY);
		return Keys.hmacShaKeyFor(keyBytes);
	}
	
	//to get all claims
	public <T> T extractClaims(String token,Function<Claims,T> claimResolver){
		final Claims claims=extractAllClaims(token);
		return claimResolver.apply(claims);
	}
	//to extract user name from token
	public String extractUserName(String token) {
		return extractClaims(token, Claims::getSubject);
	}
	//to generate token using user object
	 public String generateToken(Trainee trainee) {
	        return doGenerateToken(trainee.getEmail());
	    }
	    private String doGenerateToken(String subject) {

	        Claims claims = Jwts.claims().setSubject(subject);
//			claims.put("scopes", Arrays.asList(new SimpleGrantedAuthority("admin")));
	        Instant now = Instant.now();
	        LocalDate expirationDate = LocalDate.now().plusDays(10); // Example: token expires in 10 days
	        Instant expirationInstant = expirationDate.atStartOfDay(ZoneId.systemDefault()).toInstant();
	        return Jwts.builder()
	                .setClaims(claims)
	                .setIssuedAt(Date.from(now))
	                .setExpiration(Date.from(expirationInstant))
	                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
	                .compact();
	    }
	
//	public String generateToken(Map<String,Object> extractClaims,UserDetails userDetails) {
//		Date now = new Date(); 
//		long tenMinutesInMilliSeconds = 10 * 60 * 1000; 
//		Date expirationDate = new Date(now.getTime() + tenMinutesInMilliSeconds);
//		return Jwts.builder().setClaims(extractClaims)
//				.setSubject(userDetails.getUsername())
//				.setIssuedAt(now)
//				.setExpiration(expirationDate)
//				.signWith(getSignInKey(), SignatureAlgorithm.HS256)
//				.compact();
//	}
	    
	    //to check token valid or not
	    public boolean isTokenValid(String token,UserDetails userDetails) {
	    	final String userEmail=extractUserName(token);
	    	return (userEmail.equals(userDetails.getUsername()) && !isTokenExpired(token));
	    }
	    //to check token is expired or not
	    public boolean isTokenExpired(String token) {
	    	return extractExpirationDate(token).before(new Date());
	    }
	    private Date extractExpirationDate(String token) {
	    	return extractClaims(token, Claims::getExpiration);
	    }
}
