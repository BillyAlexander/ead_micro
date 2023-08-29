package com.ead.notification.configs.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
public class JwtProvider {
	
	@Value("${ead.auth.jwtSecret}")
	private String jwtSecret;
	
	
	public String getSubjectJwt(String token) {
		return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
	}
	
	public String getClaimNameJwt(String token, String claimName) {
		return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().get(claimName).toString();
	}
	
	public boolean validateJwt(String authToken) {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
			return true;
		} catch (SignatureException e) {
			log.error("Invalid JWT Signature: {}", e.getMessage());
		} catch (MalformedJwtException e) {
			log.error("Invalid JWT token : {}", e.getMessage());
		} catch (ExpiredJwtException e) {
			log.error(" JWT is expired: {}", e.getMessage());
		} catch (UnsupportedJwtException e) {
			log.error("JWT is unsupported: {}", e.getMessage());
		} catch (IllegalArgumentException e) {
			log.error("JWT claims string is empty: {}", e.getMessage());
		}
		return false;
	}

}
