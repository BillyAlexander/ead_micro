package com.ead.payment.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import org.springframework.beans.BeanUtils;

import com.ead.payment.dtos.PaymentEventDto;
import com.ead.payment.enums.PaymentControl;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "TB_PAYMENTS")
public class PaymentModel implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID paymentId;

	@Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PaymentControl paymentControl;
	
	@Column
    private LocalDateTime paymentRequestDate;
	
	@Column
    private LocalDateTime paymentCompletationDate;
	
	@Column
    private LocalDateTime paymentexpirationDate;
	
	@Column(nullable = false,length = 4)
    private String lastDigitsCreditCard;
	
	@Column(nullable = false)
    private BigDecimal valuePaid;
	
	@Column(length = 150)
    private String paymentMessage;
	
	@Column
    private boolean recurrence;
	
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private UserModel user;
	
	public PaymentEventDto convertToPaymentEventDto() {
		var paymentEventDto= new PaymentEventDto();
		BeanUtils.copyProperties(this, paymentEventDto);
		paymentEventDto.setPaymentControl(this.getPaymentControl().toString());
		paymentEventDto.setUserId(this.getUser().getUserId());
		return paymentEventDto;
	}
	
}
