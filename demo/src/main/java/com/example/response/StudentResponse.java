package com.example.response;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;

import org.springframework.beans.factory.annotation.Value;

import com.example.entity.Address;
import com.example.entity.Student;
import com.example.entity.Subject;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentResponse {

//@JsonIgnore
private Long id;

//@Value("${app.name:Default Demo App}") @Value is Spring bean managed 
//so doest work with pojo class only work @restcontroller, @service and @transational 
@JsonProperty("first_name")
private String firstName;

private String lastName;

private String email;

//private String fullName;

private String street;

private String city;

private List<SubjectResponse> learningSubjects;


public StudentResponse(Student student)
{
	this.id=student.getId();// you dont want show the consumer comment it, and not populate the json response
	this.firstName=student.getFirstName();
	this.lastName=student.getLastName();
	this.email=student.getEmail();
	//this.fullName=student.getFirstName() + " " +student.getLastName();
	this.street=student.getAddress().getStreet();
	this.city=student.getAddress().getCity();

	if(student.getLearningSubjects()!=null)
	{
		learningSubjects=new ArrayList<SubjectResponse>();
	for(Subject subject: student.getLearningSubjects())
	{
		learningSubjects.add(new SubjectResponse(subject));
	}
	}

}

public StudentResponse(Address address)
{
	this.id=address.getId();
	this.street=address.getStreet();
	this.city=address.getCity();
	this.firstName=address.getStudent().getFirstName();
	this.lastName=address.getStudent().getLastName();
	this.email=address.getStudent().getEmail();
	
	}
}
