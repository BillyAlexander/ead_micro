package com.ead.notification.services.impl;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ead.notification.enums.NotificationStatus;
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

	@Override
	public Page<NotificationModel> findAllNotificationByUser(UUID userId, Pageable pageable) {
		return notificationRepository.findAllByUserIdAndNotificationStatus(userId, NotificationStatus.CREATED, pageable);
	}

	@Override
	public Optional<NotificationModel> findByNotificationIdAndUserId(UUID notificationId, UUID userId) {
		
		return notificationRepository.findByNotificationIdAndUserId(notificationId, userId);
	}
}
