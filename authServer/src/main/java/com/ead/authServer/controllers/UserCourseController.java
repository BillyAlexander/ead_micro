package com.ead.authServer.controllers;

import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ead.authServer.clients.CourseClient;
import com.ead.authServer.dtos.CourseDto;
import com.ead.authServer.dtos.UserCourseDto;
import com.ead.authServer.models.UserCourseModel;
import com.ead.authServer.models.UserModel;
import com.ead.authServer.services.UserCourseService;
import com.ead.authServer.services.UserService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/users")
public class UserCourseController {
	
	@Autowired
	CourseClient userClient;
	
	@Autowired
	UserService userService;
	
	@Autowired
	UserCourseService userCourseService;
	
	@GetMapping("/{userId}/courses")
	public ResponseEntity<Object> getAllCoursesByUser(@PageableDefault(page = 0, size = 10,sort = "courseId", direction = Sort.Direction.ASC) Pageable pageable,
			@PathVariable(value = "userId") UUID userId){
		
		Optional<UserModel> userModelOptional = userService.findById(userId);
		if (!userModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
		}		
		return ResponseEntity.status(HttpStatus.OK).body(userClient.getAllCoursesByUser(userId, pageable));
	}
	
	@PostMapping("/{userId}/courses/subscription")
	public ResponseEntity<Object> saveSubscriptionUserInCourse(@PathVariable(value = "userId") UUID userId,
			@RequestBody @Valid UserCourseDto userCourseDto){
		Optional<UserModel> userModelOptional = userService.findById(userId);
		if (!userModelOptional.isPresent())
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
		
		if(userCourseService.existsByUserAndCourseId(userModelOptional.get(),userCourseDto.getCourseId())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(" course Subscripcion already exists.");
		}
		
		UserCourseModel userCourseModel = userCourseService.save(userModelOptional.get().convertToUserCourseModel(userCourseDto.getCourseId()));
		return  ResponseEntity.status(HttpStatus.CREATED).body(userCourseModel);
	}
	
	@DeleteMapping("/courses/{courseId}")
	public ResponseEntity<Object> deleteUserCourseByCourse(@PathVariable(value = "courseId") UUID courseId){
		if(!userCourseService.existsByCourseId(courseId)) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("UserCourse not found.");
		}
		
		userCourseService.deleteUserCourseByCourse(courseId);
		return ResponseEntity.status(HttpStatus.OK).body("USerCourse deleted successfully.");
	}

}
