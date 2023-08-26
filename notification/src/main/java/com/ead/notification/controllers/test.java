package com.ead.notification.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ead.notification.models.NotificationModel;
import com.ead.notification.repositories.NotificationRepository;

import lombok.extern.log4j.Log4j2;



@RestController
@RequestMapping("/notificacion")
@CrossOrigin(origins = "*", maxAge = 3600)
@Log4j2
public class test {
	@Autowired
	NotificationRepository notificationRepositor;
	

	@GetMapping("/test")
	 public List<NotificationModel> getNotificacion(){
		log.info("ingresa ");
		 return notificationRepositor.findAll();
		 
	 }
}
