package com.ead.authServer.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ead.authServer.models.UserCourseModel;
import com.ead.authServer.models.UserModel;

@Repository
public interface UserCourseRepository extends JpaRepository<UserCourseModel, UUID> {

	boolean existsByUserAndCourseId(UserModel userModel, UUID courseId);
	
	@Query(value = "select * from tb_users_courses where user_user_id = :userId", nativeQuery = true)
	List<UserCourseModel> findAllUserCourseIntoUser(@Param("userId") UUID userId);
}
