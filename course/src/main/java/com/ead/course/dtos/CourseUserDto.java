package com.ead.course.dtos;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CourseUserDto {
	private UUID courseId;
	private UUID userId;

}
