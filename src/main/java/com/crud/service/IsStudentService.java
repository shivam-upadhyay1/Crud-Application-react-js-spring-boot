package com.crud.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.crud.model.Student;

public interface IsStudentService {

	Student addStudent(Student student);
	
	List<Student> getStudents();
	
	Student updateStudent(Student student, Long id);
	
	Student getStudentById(Long id);
	
	void deleteStudent(Long id);
	
	//for pagination
    Page<Student> getAllProducts(int page, int size);
    

}
