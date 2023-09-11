package com.ead.notificationex.adapters.outboud.persistence.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ead.notificationex.core.domain.enums.NotificationStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "TB_NOTIFICATIONS")
public class NotificationEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID notificationId;
	
	@Column(nullable = false)
	private UUID userId;
	
	@Column(nullable = false, length = 150)
	private String title;
	
	@Column(nullable = false)
	private String message;
	
	@Column(nullable = false)
	@JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd'T'HH:mm:ss'Z'")
    private LocalDateTime creationDate;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private NotificationStatus notificationStatus;

	public UUID getNotificationId() {
		return notificationId;
	}

	public void setNotificationId(UUID notificationId) {
		this.notificationId = notificationId;
	}

	public UUID getUserId() {
		return userId;
	}

	public void setUserId(UUID userId) {
		this.userId = userId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}

	public NotificationStatus getNotificationStatus() {
		return notificationStatus;
	}

	public void setNotificationStatus(NotificationStatus notificationStatus) {
		this.notificationStatus = notificationStatus;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
