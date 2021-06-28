package com.example.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Address;
import com.example.entity.Student;
import com.example.request.CreateStudentRequest;
import com.example.request.InQueryRequest;
import com.example.request.UpdateStudentRequest;
import com.example.response.StudentResponse;
import com.example.service.StudentService;



@RestController
@RequestMapping("/api/student")
public class StudentController {
	
	
	// Error < warn< info< debug< trace
	@Autowired
	StudentService studentService;
	
	Logger logger=LoggerFactory.getLogger(StudentController.class);
	
	/*
	 * @Value("${app.name:Default Demo App}") private String appName;
	 * 
	 * //@GetMapping("/get")
	 * 
	 * @RequestMapping(value = "/get", method = RequestMethod.GET ) public String
	 * getStudent() { return appName; }
	 * 
	 * @RequestMapping(value = "/get1", method = RequestMethod.GET ) public
	 * StudentResponse getStudent1() { StudentResponse studentResponse=new
	 * StudentResponse(1,"john", "smith");
	 * 
	 * return studentResponse; }
	 */
	
	@RequestMapping(value = "/getAll", method = RequestMethod.GET )
	public List<StudentResponse> getAllstudents()
	{
		logger.error("Inside Error ");
		logger.warn("Inside Warning ");
		logger.info("Inside Info ");
		logger.debug("Inside Debug ");
		logger.trace("Inside Trace ");
		
		
		List<Student> studentList =studentService.getAllStudents();
		List<StudentResponse> studentResponseList=new ArrayList<StudentResponse>();
		
		studentList.stream().forEach(student -> {
			studentResponseList.add(new StudentResponse(student));
		});
		return studentResponseList;
		}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public StudentResponse creatStudent(@Valid @RequestBody CreateStudentRequest createStudentRequest)
	{
		Student student=studentService.createStudent(createStudentRequest);
		return new  StudentResponse(student);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public StudentResponse updateStudent(@Valid @RequestBody UpdateStudentRequest updateStudentRequest)
	{
		Student student=studentService.updateStudent(updateStudentRequest);
		return new StudentResponse(student);
	}
	
	/*
	 * @RequestMapping(value = "/delete", method = RequestMethod.DELETE) public
	 * String deleteStudent(@RequestParam long id) { return
	 * studentService.deleteStudent(id);
	 * 
	 * }
	 */
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public String deleteStudent(@PathVariable long id)
	{
		return studentService.deleteStudent(id);
		
	}
	
	
	@RequestMapping(value = "/getByFirstName/{firstName}", method = RequestMethod.GET)
public List<StudentResponse> getByFirstName(@PathVariable String firstName) 
{
		
		//logger.info("String= " + firstName);
List<Student> studentList = studentService.getByFirstName(firstName);	
List<StudentResponse> studentResponseList=new ArrayList<StudentResponse>();

studentList.stream().forEach(student -> {
	studentResponseList.add(new StudentResponse(student));
});
logger.info("studentResponseList= " + studentResponseList);
return studentResponseList;

}
	
	@RequestMapping(value = "/getByFirstNameAndLastName/{firstName}/{lastName}", method = RequestMethod.GET)
	public StudentResponse getByFirstNameAndLastName(@PathVariable String firstName, @PathVariable String lastName) 
	{
		//Student studentList = studentService.getByFirstNameAndLastName(firstName, lastName);	
		
		return new StudentResponse(studentService.getByFirstNameAndLastName(firstName, lastName));
	}
	
	@RequestMapping(value = "/getByFirstNameOrLastName/{firstName}/{lastName}", method = RequestMethod.GET)
	public List<StudentResponse> getByFirstNameOrLastName(@PathVariable String firstName, @PathVariable String lastName) 
	{
		List<Student> studentList = studentService.getByFirstNameOrLastName(firstName, lastName);
	
		List<StudentResponse> studentResponseList=new ArrayList<StudentResponse>();

		studentList.stream().forEach(student -> {
			studentResponseList.add(new StudentResponse(student));
		});
		return studentResponseList;
		}
	//@RequestMapping(value = "/getByFirstNameIn", method = RequestMethod.GET)
	@GetMapping("/getAllPagination")
	public List<StudentResponse> getAllPagination(@RequestParam int pageNo, @RequestParam int pageSize) 
	{
		List<Student> studentList = studentService.getAllPagination(pageNo, pageSize);
	
		List<StudentResponse> studentResponseList=new ArrayList<StudentResponse>();

		studentList.stream().forEach(student -> {
			studentResponseList.add(new StudentResponse(student));
		});
		return studentResponseList;
		}
	@GetMapping("/getByFirstNameIn")
	public List<StudentResponse> getByFirstNameIn(@RequestBody InQueryRequest inQueryRequest) 
	{
		//logger.info("InQueryRequest= " + inQueryRequest);
		List<Student> studentList = studentService.getByFirstNameIn(inQueryRequest);
	
		List<StudentResponse> studentResponseList=new ArrayList<StudentResponse>();

		studentList.stream().forEach(student -> {
			studentResponseList.add(new StudentResponse(student));
		});
		logger.info("studentResponseList = " + studentResponseList);
		return studentResponseList;
		}
	
	@GetMapping("/getAllEithSorting")
	public List<StudentResponse> getAllEithSorting() 
	{
		List<Student> studentList = studentService.getAllEithSorting();
	
		List<StudentResponse> studentResponseList=new ArrayList<StudentResponse>();

		studentList.stream().forEach(student -> {
			studentResponseList.add(new StudentResponse(student));
		});
		return studentResponseList;
		}
	@GetMapping("/like/{firstName}")
	public List<StudentResponse> like(@PathVariable String firstName) 
	{
		List<Student> studentList = studentService.like(firstName);
	
		List<StudentResponse> studentResponseList=new ArrayList<StudentResponse>();

		studentList.stream().forEach(student -> {
			studentResponseList.add(new StudentResponse(student));
		});
		return studentResponseList;
		}
	@GetMapping("/startsWith/{firstName}")
	public List<StudentResponse> strartsWith(@PathVariable String firstName) 
	{
		List<Student> studentList = studentService.strartsWith(firstName);
	
		List<StudentResponse> studentResponseList=new ArrayList<StudentResponse>();

		studentList.stream().forEach(student -> {
			studentResponseList.add(new StudentResponse(student));
		});
		return studentResponseList;

	}
	
	@PutMapping("updateFirstName/{id}/{firstName}")
	public String updateStudentWithJpql(@PathVariable Long id, @PathVariable String firstName)
	{
		return studentService.updateStudentWithJpql(id, firstName)+ "Student(s) updated";
	}
	
	
	@DeleteMapping("DeleteFirstName/{firstName}")
	public String deleteStudent (@PathVariable String firstName)
	{
		return studentService.deleteStudent(firstName)+ "Student(s) updated";
	}
	
	@GetMapping("/getByCity/{city}")
	public List<StudentResponse> getByCity(@PathVariable String city) 
	{
		List<Student> studentList = studentService.getByCity(city);
	
		List<StudentResponse> studentResponseList=new ArrayList<StudentResponse>();

		studentList.stream().forEach(student -> {
			studentResponseList.add(new StudentResponse(student));
		});
		return studentResponseList;

	}
	@GetMapping("/getAddressById/{id}")
	public StudentResponse getAddressById(@PathVariable Long id) 
	{
		/*
		 * List<Student> studentList = studentService.getAddressById(id);
		 * 
		 * List<StudentResponse> studentResponseList=new ArrayList<StudentResponse>();
		 * 
		 * studentList.stream().forEach(student -> { studentResponseList.add(new
		 * StudentResponse(student)); });
		 */
		Address address=studentService.getAddressById(id);
		return new StudentResponse(address);
		
		//return new StudentResponse(studentService.getAddressById(id));

	}
	
	
}
