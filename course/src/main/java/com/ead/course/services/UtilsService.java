package com.ead.course.services;

import java.util.UUID;

import org.springframework.data.domain.Pageable;

public interface UtilsService {

	String createUrlGetAllUsersByCourse(UUID curseId, Pageable pageable);
	String createUrlGetOneOUserById(UUID curseId);
	String createUrlpostSubscriptionUserInCourse(UUID courseId, UUID userId);
	String createUrldeleteCourseInAuthUser(UUID courseId);
}
