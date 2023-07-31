package com.ead.authServer.dtos;

import java.util.UUID;

import lombok.Data;

@Data
public class UserEventDto {

	private UUID userId;
	private String userName;
	private String email;
	private String fullName;
	private String userStatus;
	private String userType;
	private String phoneNumber;
	private String documentId;
	private String imageUrl;
	private String actionType;
}
