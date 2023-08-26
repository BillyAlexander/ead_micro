package com.ead.course.dtos;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotificationCommandDto {

	private String title;
	private String message;
	private UUID userId;
}
