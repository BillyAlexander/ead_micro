package com.ead.payment.dtos;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.Data;

@Data
public class PaymentRequestDto {

	@NotNull
	@DecimalMin(value = "0.0", inclusive = false)
	@Digits(integer = 5, fraction = 2)
    private BigDecimal valuePaid;
	
	@NotBlank
    private String cardHolderFullName;
	
	@NotBlank
    private String cardHolderCi;
	
	@NotBlank
	@Size(min = 16, max = 20)
    private String creditCardNumber; 
	
	@NotBlank
	@Size(min = 4, max = 10)
    private String expirationDate;
	
	@NotBlank
	@Size(min = 3, max = 3)
    private String cvvCode;
}
