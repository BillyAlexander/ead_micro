package com.ead.authServer.dtos;

import java.util.UUID;

import com.ead.authServer.enums.CourseLevel;
import com.ead.authServer.enums.CourseStatus;

import lombok.Data;
@Data
public class CourseDto {

	UUID courseId;
    public String name;
    public String description;
    public String imageUrl;
    public CourseStatus courseStatus;
    public UUID userInstructor;
    private CourseLevel courseLevel;
}
