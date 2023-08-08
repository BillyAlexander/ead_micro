package com.ead.notification.services.impl;

import org.springframework.stereotype.Service;

import com.ead.notification.models.NotificationModel;
import com.ead.notification.repositories.NotificationRepository;
import com.ead.notification.services.NotificacionService;

@Service
public class NotificacionServiceImpl implements NotificacionService {

	final NotificationRepository notificationRepository;
	
	public NotificacionServiceImpl(NotificationRepository notificationRepository) {
		this.notificationRepository=notificationRepository;
	}

	@Override
	public NotificationModel saveNotification(NotificationModel notificaionModel) {
		return notificationRepository.save(notificaionModel);
	}
}
