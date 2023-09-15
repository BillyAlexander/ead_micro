package com.ead.payment.services;

import java.util.Optional;

import com.ead.payment.dtos.PaymentRequestDto;
import com.ead.payment.models.PaymentModel;
import com.ead.payment.models.UserModel;

public interface PaymentService {
    PaymentModel requestPayment(PaymentRequestDto paymentRequestDto, UserModel userModel);
    
    Optional<PaymentModel> findLasPaymentByUser(UserModel userModel);
}
