package com.ead.authServer.configs.security;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ead.authServer.models.UserModel;
import com.ead.authServer.repositories.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		UserModel userModel = userRepository.findByUserName(userName)
				.orElseThrow(()-> new UsernameNotFoundException("User not found with userName:" +userName));
		
		return UserDetailsImpl.build(userModel);
	}
	
	public UserDetails loadUserById(UUID userId) throws UsernameNotFoundException {
		UserModel userModel = userRepository.findById(userId)
				.orElseThrow(()-> new AuthenticationCredentialsNotFoundException("User nor found with id: "+userId.toString()));
		
		return UserDetailsImpl.build(userModel);
	}

}
