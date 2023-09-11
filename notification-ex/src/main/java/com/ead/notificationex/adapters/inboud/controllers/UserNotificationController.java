package com.ead.notificationex.adapters.inboud.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ead.notificationex.adapters.dtos.NotificationDto;
import com.ead.notificationex.core.domain.NotificationDomain;
import com.ead.notificationex.core.domain.PageInfo;
import com.ead.notificationex.core.ports.NotificacionServicePort;




@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserNotificationController {
	
	final NotificacionServicePort notificacionServicePort;
	
	public UserNotificationController(NotificacionServicePort notificacionServicePort){
		this.notificacionServicePort=notificacionServicePort;
	}
	
	@PreAuthorize("hasAnyRole('STUDENT')")
	@GetMapping("/users/{userId}/notifications")
	public ResponseEntity<Page<NotificationDomain>> getAllNotificationByUser(@PathVariable(value = "userId") UUID userId,
			@PageableDefault(page = 0,size = 10,sort = "notificationId", direction = Sort.Direction.ASC) Pageable pageable,
			Authentication authentication){
		
		PageInfo pageInfo = new PageInfo();
		BeanUtils.copyProperties(pageable, pageInfo);
		List<NotificationDomain> notificationDomainList = notificacionServicePort.findAllNotificationByUser(userId, pageInfo);
				
		return ResponseEntity.status(HttpStatus.OK).body(new PageImpl<NotificationDomain>(notificationDomainList, pageable, notificationDomainList.size() ));
	}
	
	@PreAuthorize("hasAnyRole('STUDENT')")
	@PutMapping("/users/{userId}/notifications/{notificationId}")
	public ResponseEntity<Object> getAllNotificationByUser(@PathVariable(value = "userId") UUID userId,
			@PathVariable(value = "notificationId") UUID notificationId,
			@RequestBody @Valid NotificationDto notificationDto){
		
		Optional<NotificationDomain> notificationModelOptional = notificacionServicePort.findByNotificationIdAndUserId(notificationId,userId);
		if(notificationModelOptional.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Notification not found!");
		}
		
		notificationModelOptional.get().setNotificationStatus(notificationDto.getNotificationStatus());
		notificacionServicePort.saveNotification(notificationModelOptional.get());
		return ResponseEntity.status(HttpStatus.OK).body(notificationModelOptional.get());
		
	}

}
