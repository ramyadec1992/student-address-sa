package com.example.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.entity.Address;
import com.example.entity.Student;
import com.example.entity.Subject;
import com.example.repository.AddressRepository;
import com.example.repository.StudentRepository;
import com.example.repository.SubjectRepository;
import com.example.request.CreateStudentRequest;
import com.example.request.CreateSubjectRequest;
import com.example.request.InQueryRequest;
import com.example.request.UpdateStudentRequest;

@Service
public class StudentService {

	@Autowired
	StudentRepository studentRepository;
	
	@Autowired
	AddressRepository addressRepository;
	
	@Autowired
	SubjectRepository subjectRepository;
	
	
	public List<Student> getAllStudents()
	{
		return studentRepository.findAll();
	}
	
	public Student createStudent(CreateStudentRequest createStudentRequest)
	{
		//order flow insert data
		//insert into the address table than insert into student table then insert into the subject table
		Student student=new Student(createStudentRequest);
		
		Address address =new Address();
		address.setStreet(createStudentRequest.getStreet());
		address.setCity(createStudentRequest.getCity());
		
		address=addressRepository.save(address);
		student.setAddress(address);
		
		student=studentRepository.save(student);
		
		List<Subject> subjectList=new ArrayList<Subject>();
		// check-is there or not CreateStudentRequest.getsubjectsLearning
		
		if(createStudentRequest.getSubjectsLearning()!=null)
		{
			//CreateStudentRequest->create the list CreateSubjectRequest
		for(CreateSubjectRequest createSubjectRequest:
			createStudentRequest.getSubjectsLearning())
		{
			//get the all subject details so create new object of subject entity class
			Subject subject =new Subject();
			//get it from foreach loop object createSubjectRequest
			subject.setSubjectName(createSubjectRequest.getSubjectName());
			subject.setMarksObtained(createSubjectRequest.getMarksObtained());
			subject.setStudent(student);// set the foreginkey
			//put the all subject into the list
			subjectList.add(subject);
			//subjectRepository.save(subject);// single and one by one
		}
		
		subjectRepository.saveAll(subjectList);//list of data this preferable
		}
		
		//persist the LearningSubjects ->student table
		student.setLearningSubjects(subjectList);
		
		return student;
		
	}
	
	public Student updateStudent(UpdateStudentRequest updateStudentRequest)
	{
		Student student=studentRepository.findById(updateStudentRequest.getId()).get();
		
		if(updateStudentRequest.getFirstName()!=null && !updateStudentRequest.getFirstName().isEmpty())
		{
			student.setFirstName(updateStudentRequest.getFirstName());
		}
		
		if(updateStudentRequest.getLastName()!=null && !updateStudentRequest.getLastName().isEmpty())
		{
			student.setLastName(updateStudentRequest.getLastName());
		}
		if(updateStudentRequest.getEmail()!=null && !updateStudentRequest.getEmail().isEmpty())
		{
			student.setEmail(updateStudentRequest.getEmail());;
		}
		student=studentRepository.save(student);
		return student;
		}
	
	public String deleteStudent(long id)
	{
		studentRepository.deleteById(id);
		
		return "Student has been deletesuceefully";
	}
	
	public List<Student> getByFirstName(String firstName)
	{
		return studentRepository.findByFirstName(firstName);
	}
	
	public Student getByFirstNameAndLastName(String firstName, String lastName)
	{
	//return studentRepository.findByFirstNameAndLastName(firstName, lastName);
		return studentRepository.getByFirstNameAndLastName(firstName, lastName);
	}
	
	public List<Student> getByFirstNameOrLastName(String firstName, String lastName)
	{
	return studentRepository.findByFirstNameOrLastName(firstName, lastName);
	
	
	}
	
	public List<Student> getByFirstNameIn(InQueryRequest inQueryRequest)
	{
	return studentRepository.findByFirstNameIn(inQueryRequest.getFirstNames());
	}

	public List<Student> getAllPagination(int pageNo, int pageSize) {
		
		Pageable pageable =PageRequest.of(pageNo-1, pageSize);
		
		return studentRepository.findAll(pageable).getContent();
	}

	public List<Student> getAllEithSorting() {
		
		Sort sort=Sort.by(Sort.Direction.ASC, "firstName", "lastName", "email" );
		return studentRepository.findAll(sort);
	}

	public List<Student> like(String firstName) {
		return studentRepository.findByFirstNameContains(firstName);
	}

	public List<Student> strartsWith(String firstName) {
		return studentRepository.findByFirstNameStartsWith(firstName);
	}
	
	public Integer updateStudentWithJpql(Long id, String firstName)
	{
		return studentRepository.updateFirstName(id, firstName);
	}

	public Integer deleteStudent(String firstName) {
		return studentRepository.deleteByFirstName(firstName);
	}

	public List<Student> getByCity(String city) {
	// correct one	return studentRepository.findByAddressCity(city);
	//worng one i got a exeption Parameter value [Maharastra	//return studentRepository.findByAddress(city);
	
		return studentRepository.getByAddressCity(city);
	}

	public Address getAddressById(Long id) {
		//return addressRepository.getAddressById(id);
		Address address=addressRepository.findById(id).get();
		return address;
	}
	
}
