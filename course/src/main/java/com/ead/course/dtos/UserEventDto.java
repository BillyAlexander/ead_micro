package com.ead.course.dtos;

import java.util.UUID;

import org.springframework.beans.BeanUtils;

import com.ead.course.enums.UserStatus;
import com.ead.course.enums.UserType;
import com.ead.course.models.UserModel;

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
		userModel.setUserStatus(UserStatus.valueOf(this.userStatus));
		userModel.setUserType(UserType.valueOf(this.userType));
		return userModel;
	}
}
