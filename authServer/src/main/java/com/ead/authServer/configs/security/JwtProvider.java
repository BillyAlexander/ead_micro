package com.ead.authServer.configs.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
public class JwtProvider {
	
	@Value("${ead.auth.jwtSecret}")
	private String jwtSecret;
	@Value("${ead.auth.jwtExpirationMs}")
	private int jwtExpirationTimeMs;
	
	public String generateJwt(Authentication authentication) {
		 UserDetails userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
		 Date dateNow=new Date();
		 Date dateExpire = new Date((dateNow).getTime()+ jwtExpirationTimeMs);
		 log.info("Is Generating Compact token user: {} since:{} until:{}",userPrincipal.getUsername(), dateNow, dateExpire);
		 
		 return Jwts.builder()
				 .setSubject((userPrincipal.getUsername()))
				 .setIssuedAt(dateNow)
				 .setExpiration(dateExpire)
				 .signWith(SignatureAlgorithm.HS512, jwtSecret)
				 .compact();
	}

}
