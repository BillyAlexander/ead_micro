package com.ead.notificationex.adapters.outboud.persistence;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ead.notificationex.adapters.outboud.persistence.entities.NotificationEntity;
import com.ead.notificationex.core.domain.enums.NotificationStatus;

@Repository
public interface NotificationJpaRepository extends JpaRepository<NotificationEntity, UUID> {

	Page<NotificationEntity> findAllByUserIdAndNotificationStatus(UUID userId, NotificationStatus notificationStatus, Pageable pageable);
	
	Optional<NotificationEntity> findByNotificationIdAndUserId(UUID notificationId,UUID userId);
}
