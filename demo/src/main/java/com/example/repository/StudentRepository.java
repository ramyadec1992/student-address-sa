package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

 List<Student> findByFirstName(String firstName);

 Student findByFirstNameAndLastName(String firstName, String lastName);

 List<Student> findByFirstNameOrLastName(String firstName, String lastName);

 List<Student> findByFirstNameIn(List<String> firstNames);
 
 List<Student> findByFirstNameContains(String firstName);
 
 List<Student> findByFirstNameStartsWith(String firstName);
 
 List<Student> findByFirstNameEndsWith(String firstName);
 
 
 
 
 @Query("From Student where firstName=:firstName and lastName=:lastName")
 Student getByFirstNameAndLastName(String firstName, String lastName);
 
	/*
	 * @Query("From Student where firstName=?2 and lastName= ?1") Student
	 * getByFirstNameAndLastName(String lastName, String firstName); // not recommended
	 * 
	 * 
	 * @Query("From Student where firstName=:firstname and lastName=:lastName")
	 * Student getByFirstNameAndLastName(String lastName, @Param("firstname") String 
	 * firstName);
	 */
 
 
 @Modifying
 @Transactional
 @Query("Update Student set firstName=:firstName where id = :id")
 Integer updateFirstName(Long id, String firstName);
 
 
 @Modifying
 @Transactional
 @Query("Delete From Student where firstName=:firstName")
 Integer deleteByFirstName(String firstName);
 
 List<Student> findByAddressCity(String city);
 
 List<Student> findByAddress(String city);
 
 @Query("From Student where address.city = :city")
 List<Student> getByAddressCity(String city);
}
