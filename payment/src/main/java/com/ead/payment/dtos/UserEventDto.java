package com.ead.payment.dtos;

import java.util.UUID;

import org.springframework.beans.BeanUtils;

import com.ead.payment.models.UserModel;

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
	
	public UserModel convertToUserModel() {
		UserModel userModel= new UserModel();
		BeanUtils.copyProperties(this, userModel);
		return userModel;
	}
}
