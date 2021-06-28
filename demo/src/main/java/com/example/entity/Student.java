package com.example.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.example.request.CreateStudentRequest;
import com.example.response.StudentResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
//@AllArgsConstructor
@Entity
@Table(name="student")
public class Student {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="email")
	private String email;
	
	/*
	 * @Transient // mean internal caluculation purpose only-> show on output postman but not save database
	 *  private String fullName;
	 */
	
	@OneToOne//(fetch =FetchType.LAZY)
	@JoinColumn(name="address_id") //forgienkey @Joincolumn mean add extra column adding the table of student and refer the address entity
	//@PrimaryKeyJoinColumn
	private Address address;   //create address entity //OneToOne means one student have a one address
	
	@OneToMany(mappedBy= "student")
	private List<Subject> learningSubjects;
	
	public Student(CreateStudentRequest createStudentRequest)
	{
		
		this.firstName=createStudentRequest.getFristName();
		this.lastName=createStudentRequest.getLastName();
		this.email=createStudentRequest.getEmail();
	//this.fullName=createStudentRequest.getFristName()+ " " +createStudentRequest.getLastName();
	
	}
	

}
