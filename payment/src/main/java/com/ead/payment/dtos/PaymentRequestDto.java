package com.ead.payment.dtos;

import java.math.BigDecimal;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
