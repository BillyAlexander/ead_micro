package com.ead.notificationex.adapters.configs;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.ead.notificationex.NotificationExApplication;
import com.ead.notificationex.core.ports.NotificationPersistencePort;
import com.ead.notificationex.core.services.NotificacionServicePortImpl;

@Configuration
@ComponentScan(basePackageClasses = NotificationExApplication.class)
public class BeanConfiguration {

	@Bean
	NotificacionServicePortImpl notificacionServicePortImpl(NotificationPersistencePort persistence) {
		return new NotificacionServicePortImpl(persistence);
	}
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
