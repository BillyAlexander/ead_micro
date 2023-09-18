package com.ead.payment.dtos;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentCommandDto {

	private UUID userId;
	private UUID paymentId;
	private UUID cardId;
}
