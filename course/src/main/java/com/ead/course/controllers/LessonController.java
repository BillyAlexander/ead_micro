package com.ead.course.controllers;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ead.course.dtos.LessonDto;
import com.ead.course.models.LessonModel;
import com.ead.course.models.ModuleModel;
import com.ead.course.services.LessonService;
import com.ead.course.services.ModuleService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/modules/{moduleId}/lessons")
public class LessonController {

	@Autowired
	ModuleService moduleService;

	@Autowired
	LessonService lessonService;

	@PostMapping
	public ResponseEntity<Object> saveLesson(@PathVariable(value = "moduleId") UUID moduleId,
			@RequestBody @Valid LessonDto lessonDto) {
		Optional<ModuleModel> moduleModelOptional = moduleService.findById(moduleId);
		if (!moduleModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Module not found");
		}

		var lessonModel = new LessonModel();
		BeanUtils.copyProperties(lessonDto, lessonModel);
		lessonModel.setCreationDate(LocalDateTime.now(ZoneId.of("UTC")));
		lessonModel.setModule(moduleModelOptional.get());
		return ResponseEntity.status(HttpStatus.CREATED).body(lessonService.save(lessonModel));
	}

	@DeleteMapping("/{lessonId}")
	public ResponseEntity<Object> deteleLesson(@PathVariable(value = "moduleId") UUID moduleId,
			@PathVariable(value = "lessonId") UUID lessonId) {
		Optional<LessonModel> lessonModelOptional = lessonService.findLessonIntoModule(moduleId, lessonId);
		if (!lessonModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Lesson not found for this Module");
		}

		lessonService.delete(lessonModelOptional.get());
		return ResponseEntity.status(HttpStatus.OK).body("Lesson deleted successfully");
	}

	@PutMapping("/{lessonId}")
	public ResponseEntity<Object> updateLesson(@PathVariable(value = "lessonId") UUID lessonId,
			@PathVariable(value = "moduleId") UUID moduleId, @RequestBody @Valid LessonDto lessonDto) {

		Optional<LessonModel> lessonModelOptional = lessonService.findLessonIntoModule(moduleId, lessonId);
		if (!lessonModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Lesson not found for this Module");
		}

		var lessonModel = lessonModelOptional.get();
		BeanUtils.copyProperties(lessonDto, lessonModel);
		return ResponseEntity.status(HttpStatus.OK).body(lessonService.save(lessonModel));
	}

	@GetMapping
	public ResponseEntity<List<LessonModel>> getAllLessons(@PathVariable(value = "moduleId") UUID moduleId) {
		return ResponseEntity.status(HttpStatus.OK).body(lessonService.findAllByModule(moduleId));
	}

	@GetMapping("/{lessonId}")
	public ResponseEntity<Object> getOneLesson(@PathVariable(value = "lessonId") UUID lessonId,
			@PathVariable(value = "moduleId") UUID moduleId) {

		Optional<LessonModel> lessonModelOptional = lessonService.findLessonIntoModule(moduleId, lessonId);
		if (!lessonModelOptional.isPresent())
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Lesson not found for this Module");
		else {
			return ResponseEntity.status(HttpStatus.OK).body(lessonModelOptional.get());
		}
	}

}
