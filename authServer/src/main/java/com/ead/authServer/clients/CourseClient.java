package com.ead.authServer.clients;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.ead.authServer.dtos.CourseDto;
import com.ead.authServer.dtos.ResponsePageDto;
import com.ead.authServer.services.UtilsService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
public class CourseClient {

	@Autowired
	RestTemplate restTemplate;
	
	ResponseEntity<ResponsePageDto<CourseDto>> result = null;
	
	@Autowired
	UtilsService utilsService;
	
	@Value("${ead.api.url.course}")
	String REQUEST_URI_COURSE;

	//@Retry(name = "retryInstance",fallbackMethod = "retryFallBack") it is use to re send request but, should be very carefully
	@CircuitBreaker(name = "circuitbreakerInstance"/*, fallbackMethod = "circuitbreakerFallBack"*/)
	public Page<CourseDto> getAllCoursesByUser(UUID userId, Pageable pageable, String token) {
		List<CourseDto> searchResult = null;
		ResponseEntity<ResponsePageDto<CourseDto>> result = null;
		String url = REQUEST_URI_COURSE+utilsService.createUrl(userId, pageable);
		log.info("Request course url: {} ", url);
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", token);
		HttpEntity<String> requestEntity = new HttpEntity<String>("parameters",headers);
	
		ParameterizedTypeReference<ResponsePageDto<CourseDto>> responseType = new ParameterizedTypeReference<ResponsePageDto<CourseDto>>() {};
		log.debug("Llamando a  courserService");
		result = restTemplate.exchange(url, HttpMethod.GET, requestEntity, responseType);
		searchResult = result.getBody().getContent();
		log.debug("Response Number of Elements: {} ", searchResult.size());
		
		log.info("Ending request /courses userId {} ", userId);
		return result.getBody();
	}


	public Page<CourseDto> retryFallBack(UUID userId, Pageable pageable, Throwable t){
		log.error("Inside retry retryFallBack, cause - {} ",t.toString());
		List<CourseDto> searchResult = new ArrayList<>();
		return new PageImpl<>(searchResult);
	}
	
	public Page<CourseDto> circuitbreakerFallBack(UUID userId, Pageable pageable, Throwable t){
		log.error("Inside circuit breakerFallBack, cause - {} ",t.toString());
		List<CourseDto> searchResult = new ArrayList<>();
		return new PageImpl<>(searchResult);
	}
}
