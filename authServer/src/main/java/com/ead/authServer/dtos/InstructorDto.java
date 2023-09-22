package com.ead.authServer.dtos;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;

import lombok.Data;

@Data
public class InstructorDto {
	@NotNull
	private UUID userId; 
}
