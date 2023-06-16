package com.ead.course.services.Impl;

import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UtilsServiceImpl implements com.ead.course.services.UtilsService {
	
	String REQUEST_URI_COURSE = "http://localhost:8087";

	@Override
	public String createUrl(UUID curseId, Pageable pageable) {
		return REQUEST_URI_COURSE +  "/users?courseId=" + curseId + "&page=" + pageable.getPageNumber() + "&size="
    			+ pageable.getPageSize() + "&sort=" + pageable.getSort().toString().replaceAll(": ", ",");
	}
	

}
