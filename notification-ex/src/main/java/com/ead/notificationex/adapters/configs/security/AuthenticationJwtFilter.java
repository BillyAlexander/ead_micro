package com.ead.notificationex.adapters.configs.security;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;


import lombok.extern.log4j.Log4j2;
@Log4j2
public class AuthenticationJwtFilter extends OncePerRequestFilter {

	@Autowired
	JwtProvider jwtProvider;
	
	
	final String AUTH_LABEL = "Authorization"; 
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			String jwt = getTokenHeader(request);
			if(jwt != null && jwtProvider.validateJwt(jwt)) {
				String userId = jwtProvider.getSubjectJwt(jwt);
				String rolesStr = jwtProvider.getClaimNameJwt(jwt, "roles");
				UserDetails userDetails = UserDetailsImpl.build(UUID.fromString(userId), rolesStr);
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		} catch (Exception e) {
			log.error("Cannot set user authentication: {}",e.getMessage());
		}
		
		filterChain.doFilter(request, response);

	}
	
	private String getTokenHeader(HttpServletRequest request) {
		String headerAuth = request.getHeader(AUTH_LABEL);
		String tokenType = "Bearer ";
		
		if(StringUtils.hasText(headerAuth) || headerAuth.startsWith(tokenType)) {
			return headerAuth.substring(7, headerAuth.length());
		}
		return null;
	}

}
