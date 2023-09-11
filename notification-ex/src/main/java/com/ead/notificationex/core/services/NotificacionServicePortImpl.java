package com.ead.notificationex.core.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.ead.notificationex.core.domain.NotificationDomain;
import com.ead.notificationex.core.domain.PageInfo;
import com.ead.notificationex.core.domain.enums.NotificationStatus;
import com.ead.notificationex.core.ports.NotificacionServicePort;
import com.ead.notificationex.core.ports.NotificationPersistencePort;

public class NotificacionServicePortImpl implements NotificacionServicePort {

	private final NotificationPersistencePort notificationPersistencePort;
	
	public NotificacionServicePortImpl(NotificationPersistencePort notificationPersistencePort) {
		this.notificationPersistencePort=notificationPersistencePort;
	}

	@Override
	public NotificationDomain saveNotification(NotificationDomain notificaionModel) {
		return notificationPersistencePort.save(notificaionModel);
	}

	@Override
	public List<NotificationDomain> findAllNotificationByUser(UUID userId, PageInfo pageInfo) {
		return notificationPersistencePort.findAllByUserIdAndNotificationStatus(userId, NotificationStatus.CREATED, pageInfo);
	}

	@Override
	public Optional<NotificationDomain> findByNotificationIdAndUserId(UUID notificationId, UUID userId) {
		
		return notificationPersistencePort.findByNotificationIdAndUserId(notificationId, userId);
	}
}
