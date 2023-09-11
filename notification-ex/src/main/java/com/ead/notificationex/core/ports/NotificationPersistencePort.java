package com.ead.notificationex.core.ports;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.ead.notificationex.core.domain.NotificationDomain;
import com.ead.notificationex.core.domain.PageInfo;
import com.ead.notificationex.core.domain.enums.NotificationStatus;

public interface NotificationPersistencePort {
	
	NotificationDomain save(NotificationDomain notificationDomain);
	List<NotificationDomain> findAllByUserIdAndNotificationStatus(UUID userId, NotificationStatus notificationStatus, PageInfo pageInfo);
	Optional<NotificationDomain> findByNotificationIdAndUserId(UUID notificationId, UUID userId);
}
