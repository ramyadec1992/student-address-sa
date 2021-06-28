package com.example.request;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class UpdateStudentRequest {

	@NotNull(message="student Id is required")
	private Long id;
	
	
	private String firstName;
	
	
	private  String lastName;
	
	
	private String email;
}
