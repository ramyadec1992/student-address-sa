package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.entity.Address;
import com.example.entity.Student;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long>{


	//@Query("From address where id=:id")
	//Address getAddressById(Long id);

	
}
