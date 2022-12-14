package com.ead.course.dtos;

import com.ead.course.enums.CourseLevel;
import com.ead.course.enums.CourseStatus;
import lombok.Data;

import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CourseDto {

	@NotBlank
    public String name;
	@NotBlank
    public String description;
    public String imageUrl;
    @NotNull
    public CourseStatus courseStatus;
    @NotNull
    public UUID userInstructor;
    @NotNull
    private CourseLevel courseLevel;
}
