package com.ead.payment.services.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ead.payment.models.UserModel;
import com.ead.payment.repositories.UserRepository;
import com.ead.payment.services.UserService;

@Service
public class ServiceUserImpl implements UserService {

	@Autowired
	UserRepository userRepository;
	
	@Transactional
	@Override
	public UserModel save(UserModel userModel) {
		return userRepository.save(userModel);
	}

	@Transactional
	@Override
	public void delete(UUID userId) {
		userRepository.deleteById(userId);
		
	}

}
