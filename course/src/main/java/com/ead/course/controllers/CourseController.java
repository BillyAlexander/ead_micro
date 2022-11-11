package com.ead.course.controllers;

import com.ead.course.dtos.CourseDto;
import com.ead.course.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/courses")
@CrossOrigin(origins = "*", maxAge = "3600")
public class CourseController {

    @Autowired
    CourseService courseService;

    @PostMapping("/save")
    public ResponseEntity<Object> saveCourse( CourseDto courseDto){

    }
}
