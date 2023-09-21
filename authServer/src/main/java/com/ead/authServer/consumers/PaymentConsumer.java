package com.ead.authServer.consumers;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.ead.authServer.dtos.PaymentEventDto;
import com.ead.authServer.enums.PaymentControl;
import com.ead.authServer.enums.RoleType;
import com.ead.authServer.enums.UserType;
import com.ead.authServer.models.UserModel;
import com.ead.authServer.services.RoleService;
import com.ead.authServer.services.UserService;

import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
public class PaymentConsumer {
 
	@Autowired
	UserService userService;
	
	@Autowired
	RoleService roleService;
	
	@RabbitListener(bindings = @QueueBinding(
				  value = @Queue(value = "${ead.broker.queue.paymentEventQueue.name}", durable = "true"),
				  exchange = @Exchange(value = "${ead.broker.exchange.paymentEvent}", type = ExchangeTypes.FANOUT, ignoreDeclarationExceptions = "true")
				)
			)
	public void listenPaymentEvent(@Payload PaymentEventDto paymentEventDto) {
		UserModel userModel = userService.findById(paymentEventDto.getUserId()).get();
		var roleModel = roleService.findByRoleName(RoleType.ROLE_STUDENT).orElseThrow(()-> new RuntimeException("Error role is not found"));
		
		switch (PaymentControl.valueOf(paymentEventDto.getPaymentControl())) {
		case EFFECTED:
			if(userModel.getUserType().equals(UserType.USER)) {
				userModel.setUserType(UserType.STUDENT);
				userModel.getRoles().add(roleModel);
				userService.updateUser(userModel);
			}			
			break;
		case REFUSED:
			if(userModel.getUserType().equals(UserType.STUDENT)) { // por propagacion de eventos 
				userModel.setUserType(UserType.USER);
				userModel.getRoles().remove(roleModel);
				userService.updateUser(userModel);
			}
			
			break;	
		case ERROR:
			log.error("Payment with error");
			break;	
		default:
			break;
		}
	}
}
