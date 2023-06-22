package com.ead.course.services.Impl;

import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UtilsServiceImpl implements com.ead.course.services.UtilsService {
	
	

	@Override
	public String createUrlGetAllUsersByCourse(UUID curseId, Pageable pageable) {
		return "/users?courseId=" + curseId + "&page=" + pageable.getPageNumber() + "&size="
    			+ pageable.getPageSize() + "&sort=" + pageable.getSort().toString().replaceAll(": ", ",");
	}

	@Override
	public String createUrlGetOneOUserById(UUID curseId) {
		return "/users/"+curseId;
	}

	@Override
	public String createUrlpostSubscriptionUserInCourse(UUID courseId, UUID userId) {
		return "/users/"+userId+"/courses/subscription";
	}

	@Override
	public String createUrldeleteCourseInAuthUser(UUID courseId) {
		return "/users/courses/"+courseId;
	}
	

}
