package com.ead.payment.services.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ead.payment.enums.PaymentControl;
import com.ead.payment.models.CreditCardModel;
import com.ead.payment.models.PaymentModel;
import com.ead.payment.services.PaymentStripeService;
import com.stripe.Stripe;
import com.stripe.exception.CardException;
import com.stripe.model.PaymentIntent;
import com.stripe.model.PaymentMethod;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class PaymentStripeServiceImpl implements PaymentStripeService {

	@Value(value= "${ead.stripe.secretKey}")
	private String secretKeyStripe;
	String paymentIntentId = null;
	
	
	@Override
	public PaymentModel processStripePayment(PaymentModel paymentModel, CreditCardModel creditCardMode) {
		
		Stripe.apiKey=secretKeyStripe;
		
		
		try {
			//paso1 createpaymentintent
			List<Object> paymentMethodTypes = new ArrayList<>();
			paymentMethodTypes.add("card");
			Map<String, Object> paramsPaymentIntent = new  HashMap<>();
			paramsPaymentIntent.put("amount", paymentModel.getValuePaid().multiply(new BigDecimal("100")).longValue());
			paramsPaymentIntent.put("currency", "brl");
			paramsPaymentIntent.put("payment_method_types", paymentMethodTypes);
			PaymentIntent paymentIntent = PaymentIntent.create(paramsPaymentIntent);
			paymentIntentId = paymentIntent.getId();
			log.info("++++++++++++++paymentIntent++++++++++++++++++++++++++++++");
			log.info(paymentIntent);
			//paso 2 paymentMethod I need the response of stripe, because I need to work with card tokens, I send a request to enable
			Map<String, Object> card = new HashMap<>();
			card.put("number", creditCardMode.getCreditCardNumber().replaceAll(" ", ""));
			card.put("exp_month", creditCardMode.getExpirationDate().split("/")[0]);
			card.put("exp_year", creditCardMode.getExpirationDate().split("/")[1]);
			card.put("cvc", creditCardMode.getCvvCode());			
			Map<String, Object> paramsPaymentMethod = new HashMap<>();
			paramsPaymentMethod.put("type", "card");
			paramsPaymentMethod.put("card", card);
			PaymentMethod paymentMethod= PaymentMethod.create(paramsPaymentMethod);
			log.info("++++++++++++++paymentMethod++++++++++++++++++++++++++++++");
			log.info(paymentMethod);
			
			//paso3 
			Map<String, Object> paramsPaymentConfirm = new HashMap<>();
			paramsPaymentConfirm.put("payment_method", paymentMethod.getId());
			PaymentIntent confirmPaymentIntent = paymentIntent.confirm(paramsPaymentConfirm);
			log.info("++++++++++++++confirmPaymentIntent++++++++++++++++++++++++++++++");
			log.info(confirmPaymentIntent);
			
			if(confirmPaymentIntent.getStatus().equals("succeeded")){
				paymentModel.setPaymentControl(PaymentControl.EFFECTED);
				paymentModel.setPaymentMessage("payment efected - paymentIntentId: " + paymentIntentId);
				paymentModel.setPaymentCompletationDate(LocalDateTime.now(ZoneId.of("UTC")));
			}else {
				paymentModel.setPaymentControl(PaymentControl.ERROR);
				paymentModel.setPaymentMessage("payment error v1 - paymentIntendId: "+ paymentIntentId);
			}
			
		} catch (CardException cardException) {
			try {
				paymentModel.setPaymentControl(PaymentControl.REFUSED);
				PaymentIntent paymentIntent = PaymentIntent.retrieve(paymentIntentId);
				paymentModel.setPaymentMessage("payment refused v1 - paymentIntendId: "+ paymentIntentId+ ", cause: "+paymentIntent.getLastPaymentError().getCode()+", message: "+ paymentIntent.getLastPaymentError().getMessage());
				
			} catch (Exception e) {
				paymentModel.setPaymentMessage("payment refused v1 - paymentIntendId: "+ paymentIntentId);
				log.info("Another problem occured, maybe unrelated to Stripe.");
			}
			log.info("cause: "+cardException.getMessage());
		}catch (Exception exception) {						
			paymentModel.setPaymentControl(PaymentControl.ERROR);
			paymentModel.setPaymentMessage("payment error v2 - paymentIntendId: "+ paymentIntentId);
			log.info("cause: "+exception.getMessage());
			log.info("Another problem occurred, maybe unrelated to Stripe");
		}
		
		return paymentModel;
	}

}
