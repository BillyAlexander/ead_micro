package com.ead.payment.publishers;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.ead.payment.dtos.PaymentCommandDto;

@Component
public class PaymentCommandPublisher {
	@Autowired
	RabbitTemplate rabbitTemplate;
	
	@Value(value="${ead.broker.exchange.paymentCommandExchange}")
	private String paymentCommandExchange;
	
	@Value(value="${ead.broker.key.paymentCommandKey}")
	private String paymentCommandKey;
	
	public void publishPaymentCommand(PaymentCommandDto paymentCommandDto) {
		rabbitTemplate.convertAndSend(paymentCommandExchange, paymentCommandKey, paymentCommandDto);
	}
}
