package com.ead.authServer.services.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

	@Override
	public boolean existsByCourseId(UUID courseId) {
		return userCourseRepository.existsByCourseId( courseId);
	}

	@Transactional
	@Override
	public void deleteUserCourseByCourse(UUID courseId) {
		userCourseRepository.deleteAllByCourseId(courseId);
		
	}
}
