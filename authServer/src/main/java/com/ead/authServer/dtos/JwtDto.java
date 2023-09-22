package com.ead.authServer.dtos;

import jakarta.validation.constraints.NotNull;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
@Getter @Setter
@RequiredArgsConstructor
public class JwtDto {
	@NotNull
	private String token;
	private String type="Bearer";
	
	public JwtDto(@NotNull String token) {
		this.token = token;
	}
	
}
