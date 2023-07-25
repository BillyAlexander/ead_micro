package com.ead.course.validation;



import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.ead.course.dtos.CourseDto;

@Component
public class CourseValidator implements Validator {

	@Autowired
	@Qualifier("defaultValidator")
	private Validator validator;
	
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
	
	private void validateUserInstructor(UUID userInstructor, Errors errors ) {
//		UserDto responseInstructor;
//		try {
//			responseInstructor=authUserClient.getOneUserById(userInstructor);
//			if(responseInstructor.getUserType().equals(UserType.STUDENT)) {
//				errors.rejectValue("userInstructor", "userInstructorError", "User must be INSTRUCTOR or ADMIN.");
//			}
//		} catch (HttpStatusCodeException e) {
//			if(e.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
//				errors.rejectValue("userInstructor", "userInstructorError", "INSTRUCTOR not found.");
//			}
//		}
	}

}
