package com.ead.authServer.configs.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class WebConfigSecurity extends WebSecurityConfigurerAdapter {
	private static final String[] AUTH_WHITE_LIST = {"/auth/**"};
	
	@Autowired
	UserDetailsServiceImpl  UserDetailsService;
	
	@Autowired
	AuthenticationEntryPointImpl authenticationEntryPointImpl;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.
		httpBasic()
		.authenticationEntryPoint(authenticationEntryPointImpl)
		.and()
		.authorizeRequests()
		.antMatchers(AUTH_WHITE_LIST).permitAll()
		.antMatchers(HttpMethod.GET, "/users/**").hasRole("ADMIN")
		.anyRequest().authenticated()
		.and()
		.csrf().disable()
		.formLogin();
		
	}
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(UserDetailsService).passwordEncoder(passwordEncoder());
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
