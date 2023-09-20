package com.ead.payment.publishers;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.ead.payment.dtos.PaymentEventDto;

@Component
public class PaymentEventPublisher {
	@Autowired
	RabbitTemplate rabbitTemplate;
	
	@Value(value="${ead.broker.exchange.paymentEventExchange}")
	private String exchangePaymentEvent;
	
	public void publisherPaymentEvent(PaymentEventDto paymentEventDto) {
		rabbitTemplate.convertAndSend(exchangePaymentEvent,"",paymentEventDto);
	}
}
