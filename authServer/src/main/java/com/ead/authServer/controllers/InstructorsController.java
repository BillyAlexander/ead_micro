package com.ead.authServer.controllers;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ead.authServer.dtos.InstructorDto;
import com.ead.authServer.enums.UserType;
import com.ead.authServer.models.UserModel;
import com.ead.authServer.services.UserService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/instructors")
public class InstructorsController {

	@Autowired
	UserService userService;

	@PostMapping("/subscription")
	public ResponseEntity<Object> saveSubscriptionInstructor(@RequestBody @Valid InstructorDto instructorDto) {
		Optional<UserModel> userModelOptional = userService.findById(instructorDto.getUserId());
		if (!userModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
		} else {
			var userModel = userModelOptional.get();
			userModel.setUserType(UserType.INSTRUCTOR);
			userModel.setLastUpdateDate(LocalDateTime.now(ZoneId.of("UTC")));
			userService.save(userModel);
			return ResponseEntity.status(HttpStatus.OK).body(userModel);
		}

	}

}
