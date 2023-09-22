package com.ead.payment.consumers;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.ead.payment.dtos.UserEventDto;
import com.ead.payment.enums.ActionType;
import com.ead.payment.enums.PaymentStatus;
import com.ead.payment.models.UserModel;
import com.ead.payment.services.UserService;

import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
public class UserConsumer {
	
	@Autowired
	UserService userService;

	@RabbitListener(bindings = @QueueBinding(
			value = @Queue(value = "${ead.broker.queue.userEventQueue.name}", durable = "true"),
			exchange = @Exchange(value = "${ead.broker.exchange.userEventExchange}", type = ExchangeTypes.FANOUT, ignoreDeclarationExceptions = "true")
		)
	)
	public void listenUserEvent(@Payload UserEventDto userEventDto) {

		
		log.info("UserEvent: "+userEventDto.getActionType());
		switch (ActionType.valueOf(userEventDto.getActionType())) {
		case CREATE:
				var userModel = userEventDto.convertToUserModel(new UserModel());
				userModel.setPaymentStatus(PaymentStatus.NOTSTARTED);
				userService.save(userModel);
			break;
		case UPDATE:
			userService.save(userEventDto.convertToUserModel(userService.findById(userEventDto.getUserId()).get()));
		break;
		case DELETE:
			userService.delete(userEventDto.getUserId());
		break;
		}
		
	}
}