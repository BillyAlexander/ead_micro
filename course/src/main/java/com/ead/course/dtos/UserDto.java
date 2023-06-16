package com.ead.course.dtos;

import java.util.UUID;

import com.ead.course.enums.UserStatus;
import com.ead.course.enums.UserType;

import lombok.Data;

@Data
public class UserDto {

	private UUID userId;
	private String userName;
	private String email;
	private String fullName;
	private UserStatus userStatus;
	private UserType userType;
	private String phoneNumber;
	private String documentId;
	private String imageUrl;
}
