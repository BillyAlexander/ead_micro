package com.ead.authServer.services;

import java.util.UUID;

import org.springframework.data.domain.Pageable;

public interface UtilsService {

	String createUrl(UUID userId, Pageable page);

	String createUrldeleteUserInCourse(UUID userId);
}
