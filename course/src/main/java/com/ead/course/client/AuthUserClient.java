package com.ead.course.client;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.ead.course.dtos.CourseUserDto;
import com.ead.course.dtos.ResponsePageDto;
import com.ead.course.dtos.UserDto;
import com.ead.course.services.UtilsService;

import lombok.extern.log4j.Log4j2;


@Log4j2
@Component
public class AuthUserClient {
	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	UtilsService utilsService;
	
	@Value("${ead.api.url.authuser}")
	String REQUEST_URL_AUTHUSER;
	
	ResponseEntity<ResponsePageDto<UserDto>> result = null;
	
	public Page<UserDto> getAllUsersByCourse(UUID courseId, Pageable pageable) {
		List<UserDto> searchResult = null;
		ResponseEntity<ResponsePageDto<UserDto>> result = null;
		String url = REQUEST_URL_AUTHUSER+utilsService.createUrlGetAllUsersByCourse(courseId, pageable);
		log.info("Request course url: {} ", url);
		try {
			ParameterizedTypeReference<ResponsePageDto<UserDto>> responseType = new ParameterizedTypeReference<ResponsePageDto<UserDto>>() {
			};
			result = restTemplate.exchange(url, HttpMethod.GET, null, responseType);
			searchResult = result.getBody().getContent();
			log.debug("Response Number of Elements: {} ", searchResult.size());
		} catch (HttpStatusCodeException e) {
			log.error("Error request /courses {} ", e);
		}
		log.info("Ending request /users courseId {} ", courseId);
		return result.getBody();
	}
	
	public UserDto getOneUserById(UUID userId) {
		String url = REQUEST_URL_AUTHUSER+utilsService.createUrlGetOneOUserById(userId);
		return restTemplate.exchange(url, HttpMethod.GET, null, UserDto.class).getBody();
	}

	public void postSubscriptionUserInCourse(UUID courseId, UUID userId) {
		String url = REQUEST_URL_AUTHUSER+utilsService.createUrlpostSubscriptionUserInCourse(courseId,userId);
		CourseUserDto cudto = new CourseUserDto(courseId, userId);
		restTemplate.postForObject(url, cudto, String.class);		
	}

	public void deleteCourseInAuthUser(UUID courseId) {
		String url = REQUEST_URL_AUTHUSER+utilsService.createUrldeleteCourseInAuthUser(courseId);
		restTemplate.exchange(url, HttpMethod.DELETE, null, String.class);
		
	}

}
