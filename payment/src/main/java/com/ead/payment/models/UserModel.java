package com.ead.payment.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.ead.payment.enums.PaymentStatus;
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
@Table(name = "TB_USERS")
public class UserModel implements Serializable {
	
	private static final long serialVersionUID=1L;
	
	@Id
    private UUID userId;

	@Column(nullable = false,unique = true, length = 50)
    private String email;
	
	@Column(nullable = false,length = 150)
    private String fullName;
	
	@Column(nullable = false)
    private String userStatus;
    @Column(nullable = false)
    private String userType;
    
    @Column(length = 20)
    private String documentId;
    @Column(length = 20)
    private String phoneNumber;
    
    
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;
    
    @Column
    private LocalDateTime paymentExpirationDate;
    
    @Column
    private LocalDateTime firstPaymentDate;
    
    @Column
    private LocalDateTime lastPaymentDate;
    
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @Fetch(FetchMode.SUBSELECT)
    private Set<PaymentModel> payments;
	
}
