package com.ead.authServer.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ead.authServer.clients.UserClient;
import com.ead.authServer.dtos.CourseDto;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/users")
public class UserCourseController {
	
	@Autowired
	UserClient userClient;
	
	@GetMapping("/{userId}/courses")
	public ResponseEntity<Page<CourseDto>> getAllCoursesByUser(@PageableDefault(page = 0, size = 10,sort = "courseId", direction = Sort.Direction.ASC) Pageable pageable,
			@PathVariable(value = "userId") UUID userId){
		return ResponseEntity.status(HttpStatus.OK).body(userClient.getAllCoursesByUser(userId, pageable));
	}

}
