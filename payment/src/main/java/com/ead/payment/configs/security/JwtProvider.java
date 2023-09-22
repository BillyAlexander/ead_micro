package com.ead.payment.configs.security;

import java.nio.charset.StandardCharsets;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecurityException;
import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
public class JwtProvider {

	@Value("${ead.auth.jwtSecret}")
	private String jwtSecret;

	// view in git
	private SecretKey convertSecretKey() {
		return Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
	}

	public String getSubjectJwt(String token) {
		return Jwts.parserBuilder().setSigningKey(convertSecretKey()).build().parseClaimsJws(token).getBody().getSubject();
	}

	public String getClaimNameJwt(String token, String claimName) {
		return Jwts.parserBuilder().setSigningKey(convertSecretKey()).build().parseClaimsJws(token).getBody().get(claimName).toString();
	}

	public boolean validateJwt(String authToken) {
		try {
			Jwts.parserBuilder().setSigningKey(convertSecretKey()).build().parseClaimsJws(authToken);
			return true;
		} catch (SecurityException e) {
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
