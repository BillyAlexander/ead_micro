package com.ead.notificationex.core.ports;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.ead.notificationex.core.domain.NotificationDomain;
import com.ead.notificationex.core.domain.PageInfo;

public interface NotificacionServicePort {

	NotificationDomain saveNotification(NotificationDomain notificationDomain);

	List<NotificationDomain> findAllNotificationByUser(UUID userId, PageInfo pageable);

	Optional<NotificationDomain> findByNotificationIdAndUserId(UUID notificationId, UUID userId);

}
