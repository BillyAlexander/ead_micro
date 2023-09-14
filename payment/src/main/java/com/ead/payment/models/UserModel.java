package com.ead.payment.models;

import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.usertype.UserType;

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
	
}
