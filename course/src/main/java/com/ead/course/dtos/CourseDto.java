package com.ead.course.dtos;

import com.ead.course.enums.CourseLevel;
import com.ead.course.enums.CourseStatus;
import lombok.Data;

import java.util.UUID;

@Data
public class CourseDto {

    public String name;
    public String description;
    public String imageUrl;
    public CourseStatus courseStatus;
    public UUID userInstructor;
    private CourseLevel courseLevel;
}
