package com.example.request;

import java.util.List;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class CreateStudentRequest {

    @JsonProperty("first_name")
    @NotBlank(message="firstname is requried")
	private String fristName;
	
	private String lastName;
	
	private String email;
	
	
	private String street;
	
	private String city;
	
	private List<CreateSubjectRequest> subjectsLearning;
	
	
    
    
    
}
