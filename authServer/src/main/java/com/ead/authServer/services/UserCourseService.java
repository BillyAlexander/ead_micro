package com.ead.authServer.services;

import java.util.UUID;

import com.ead.authServer.models.UserCourseModel;
import com.ead.authServer.models.UserModel;

public interface UserCourseService {

	boolean existsByUserAndCourseId(UserModel userModel, UUID courseId);

	UserCourseModel save(UserCourseModel userCourseModel);

}
