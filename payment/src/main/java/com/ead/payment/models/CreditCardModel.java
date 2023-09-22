package com.ead.payment.models;

import java.io.Serializable;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "TB_CREDIT_CARDS")
public class CreditCardModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID cardId;
	
	@Column(nullable = false,length = 150)
    private String cardHolderFullName;
	
	@Column(nullable = false,length = 20)
    private String cardHolderCi;
	
	@Column(nullable = false,length = 20)
    private String creditCardNumber; // crypto en prod
	
	@Column(nullable = false,length = 20)
    private String expirationDate;
	
	@Column(nullable = false,length = 3)
    private String cvvCode;
	
	@OneToOne
	@JoinColumn(name="user_id", unique = true, nullable = false)
	private UserModel user;
}
