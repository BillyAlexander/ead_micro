package com.ead.notification.dtos;

import javax.validation.constraints.NotNull;

import com.ead.notification.enums.NotificationStatus;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class NotificationDto {
	
	@NotNull
	private NotificationStatus notificationStatus;

}
