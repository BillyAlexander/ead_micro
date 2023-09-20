package com.ead.payment.consumers;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.ead.payment.dtos.PaymentCommandDto;
import com.ead.payment.services.PaymentService;

import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
public class PaymentConsumer {
	@Autowired
	PaymentService paymentService;
	
	@RabbitListener(bindings = @QueueBinding(
			value = @Queue(value = "${ead.broker.queue.paymentCommandQueue.name}", durable = "true"),
			exchange = @Exchange(value="${ead.broker.exchange.paymentCommandExchange}",type = ExchangeTypes.FANOUT),
			key = "${ead.broker.key.paymentCommandKey}")
			)
	public void listenUserEvent(@Payload PaymentCommandDto paymentCommandDto) {
		log.info("------------------------------------");
		log.info("paymentCommandDto.getPaymentId(): "+paymentCommandDto.getPaymentId());
		log.info("paymentCommandDto.getUserId(): "+paymentCommandDto.getUserId());
		paymentService.makePayment(paymentCommandDto);
	}
}
