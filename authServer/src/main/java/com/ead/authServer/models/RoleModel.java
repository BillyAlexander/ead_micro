package com.ead.authServer.models;

import java.io.Serializable;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

import com.ead.authServer.enums.RoleType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="TB_ROLES")
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)

public class RoleModel implements GrantedAuthority,Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID roleId;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false,unique=true,length = 30)
	private RoleType roleName;

	@Override
	@JsonIgnore
	public String getAuthority() {
		return this.roleName.toString();
	}
	
	
}
