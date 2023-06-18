package com.ead.authServer.services.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ead.authServer.models.UserCourseModel;
import com.ead.authServer.models.UserModel;
import com.ead.authServer.repositories.UserCourseRepository;
import com.ead.authServer.services.UserCourseService;

@Service
public class UserCourseServiceImpl implements UserCourseService {
	@Autowired
	UserCourseRepository userCourseRepository;

	@Override
	public boolean existsByUserAndCourseId(UserModel userModel, UUID courseId) {		
		return userCourseRepository.existsByUserAndCourseId(userModel, courseId);
	}

	@Override
	public UserCourseModel save(UserCourseModel userCourseModel) {		
		return userCourseRepository.save(userCourseModel);
	}
}
