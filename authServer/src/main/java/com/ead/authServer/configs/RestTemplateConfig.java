package com.ead.authServer.configs;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {
	
	@Value("${ead.api.url.timeOutConnect}")
	int TIME_OUT_CONNECT;
	@Value("${ead.api.url.timeOutRead}")
	int TIME_OUT_READ;
	
	@LoadBalanced
	@Bean
	public RestTemplate resTemplate(RestTemplateBuilder builder) {
		return builder
				.setConnectTimeout(Duration.ofMillis(TIME_OUT_CONNECT))
				.setReadTimeout(Duration.ofMillis(TIME_OUT_READ))
				.build();
	}
}
