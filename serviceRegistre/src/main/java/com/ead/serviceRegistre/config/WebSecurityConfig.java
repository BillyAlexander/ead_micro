package com.ead.serviceRegistre.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {
	
	@Value("${ead.serviceRegistre.userName}")
	private String userName;
	
	@Value("${ead.serviceRegistre.password}")
	private String password;
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.
		httpBasic(Customizer.withDefaults())
		.authorizeHttpRequests((authorize) -> authorize.anyRequest().authenticated())
		.csrf(crsf-> crsf.disable())
		.formLogin(Customizer.withDefaults());		
		return http.build();
	}
	
	@Bean
	public InMemoryUserDetailsManager userDetailService() {
		UserDetails user = User.withUsername(userName)
				.password(passwordEncoder().encode(password))
				.roles("ADMIN")
				.build();
		return new InMemoryUserDetailsManager(user);
		
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
