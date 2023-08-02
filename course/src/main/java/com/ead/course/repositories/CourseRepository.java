package com.ead.course.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ead.course.models.CourseModel;

public interface CourseRepository extends JpaRepository<CourseModel, UUID>, JpaSpecificationExecutor<CourseModel> {
	
	@Query(value = "SELECT CASE WHEN count(tcu.course_id)>0 THEN true ELSE false END AS answer"
			+ " FROM tb_courses_users AS tcu"
			+ " where tcu.course_id= :courseId"
			+ " AND tcu.user_id= :userId", nativeQuery = true)
	boolean existsByCourseAndUser(@Param("courseId") UUID courseId, @Param("userId")  UUID userId);
	
	@Modifying
	@Query(value = "INSERT INTO tb_courses_users(course_id, user_id) VALUES (:courseId,:userId)", nativeQuery = true)
	void saveCourseUser(@Param("courseId") UUID courseId, @Param("userId")  UUID userId);
}
