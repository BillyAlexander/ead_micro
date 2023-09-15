package com.ead.payment.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.ead.payment.models.PaymentModel;
import com.ead.payment.models.UserModel;

@Repository
public interface PaymentRepository extends JpaRepository<PaymentModel, UUID>, JpaSpecificationExecutor<PaymentModel> {

	Optional<PaymentModel> findTopByUserOrderByPaymentRequestDateDesc(UserModel userModel);
}
