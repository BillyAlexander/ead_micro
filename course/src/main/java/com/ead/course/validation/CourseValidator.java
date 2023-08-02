package com.ead.course.validation;



import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.ead.course.dtos.CourseDto;
import com.ead.course.enums.UserType;
import com.ead.course.models.UserModel;
import com.ead.course.services.UserService;

@Component
public class CourseValidator implements Validator {

	@Autowired
	@Qualifier("defaultValidator")
	private Validator validator;
	
	@Autowired
	UserService userService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void validate(Object target, Errors errors) {
		CourseDto courseDto = (CourseDto) target;
		validator.validate(target, errors);
		if(!errors.hasErrors()) {
			validateUserInstructor(courseDto.getUserInstructor(),errors);
		}
		
	}
	
	private void validateUserInstructor(UUID userInstructorId, Errors errors ) {
		Optional<UserModel> userModelOptional = userService.findByUd(userInstructorId);
		if(!userModelOptional.isPresent()) {
			errors.rejectValue("userInstructor", "userInstructorError", "INSTRUCTOR not found.");
			return;
		}
		if(userModelOptional.get().getUserType().equals(UserType.STUDENT)) {
			errors.rejectValue("userInstructor", "userInstructorError", "User must be INSTRUCTOR or ADMIN.");
		}
	}

}
