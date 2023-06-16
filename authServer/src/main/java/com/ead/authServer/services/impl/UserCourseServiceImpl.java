package com.ead.authServer.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ead.authServer.repositories.UserCourseRepository;
import com.ead.authServer.services.UserCourseService;

@Service
public class UserCourseServiceImpl implements UserCourseService {
	@Autowired
	UserCourseRepository userCourseRepository;
}
