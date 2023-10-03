package com.crud.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.crud.model.Student;
import com.crud.service.IsStudentService;
@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/students")
public class StudentController {

	private final IsStudentService studentService;

	public StudentController(IsStudentService studentService) {
		super();
		this.studentService = studentService;
	}
	
	//get All Students
	@GetMapping
	public ResponseEntity<List<Student>> getStudents()
	{
		return new ResponseEntity<>(studentService.getStudents(),HttpStatus.FOUND);
	}
	
	//add Student
	@PostMapping
	public Student addStudent(@RequestBody Student student)
	{
		return studentService.addStudent(student);
	}
	
	//update student
	@PutMapping("/update/{id}")
	public Student updateStudent(@RequestBody Student student,@PathVariable("id") Long id)
	{
		return studentService.updateStudent(student, id);
	}
	
	//delete student
	@DeleteMapping("/delete/{id}")
	public void deletStudent(@PathVariable("id") Long id)
	{
		 studentService.deleteStudent(id);
	}
	
	//get single student
	@GetMapping("{id}")
	public Student getStudentById(@PathVariable("id") Long id)
	{
		return studentService.getStudentById(id);
	}
	
	//pagination handler method
    @GetMapping("/pagination")
    public Page<Student> getAllProducts(@RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "10") int size) {
        return studentService.getAllProducts(page, size);
    }
    
  
	
}
