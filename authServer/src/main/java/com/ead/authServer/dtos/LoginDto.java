package com.ead.authServer.dtos;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class LoginDto {
	@NotBlank
	private String userName;
	@NotBlank
	private String password;
}