package com.ead.payment.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.ead.payment.dtos.PaymentCommandDto;
import com.ead.payment.dtos.PaymentRequestDto;
import com.ead.payment.models.PaymentModel;
import com.ead.payment.models.UserModel;

public interface PaymentService {
    PaymentModel requestPayment(PaymentRequestDto paymentRequestDto, UserModel userModel);
    
    Optional<PaymentModel> findLasPaymentByUser(UserModel userModel);
    
    Page<PaymentModel> findAllByUser(Specification<PaymentModel> spec, Pageable pageable);
    
    Optional<PaymentModel> findPaymentByUser(UUID userId, UUID paymentId);
    
    void makePayment(PaymentCommandDto paymentCommandDto);
}
