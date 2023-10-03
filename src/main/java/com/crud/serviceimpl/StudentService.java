package com.crud.serviceimpl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.crud.exceptions.StudentAlreadyExistsException;
import com.crud.exceptions.StudentNotFoundException;
import com.crud.model.Student;
import com.crud.repository.StudentRepository;
import com.crud.service.IsStudentService;


@Service
public class StudentService implements IsStudentService {

	private final StudentRepository studentRepository;
	
	
	public StudentService(StudentRepository studentRepository) {
		super();
		this.studentRepository = studentRepository;
	}

	@Override
	public Student addStudent(Student student)
	{
		//check if Student is exist or not in a database
		if(studentAlreadyExists(student.getEmail()))
		{
			throw new StudentAlreadyExistsException(student.getEmail()+"  already exists !");
		}
		
		return studentRepository.save(student);
	}


	@Override
	public List<Student> getStudents() {
		return studentRepository.findAll();
	}

	@Override
	public Student updateStudent(Student student, Long id) {
		return studentRepository.findById(id).map(st ->{  //first check user exist or not then we use method chaing
			st.setFirstName(student.getFirstName());  //through method retrive the data and set the data
			st.setLastName(student.getLastName());
			st.setEmail(student.getEmail());
			st.setDepartment(student.getDepartment());
			return studentRepository.save(st);
		}).orElseThrow(() -> new StudentNotFoundException("Sorry, This student could not be found..!"));
	}

	@Override
	public Student getStudentById(Long id) {
		return studentRepository.findById(id).orElseThrow(()-> new StudentNotFoundException("Sorry, no student found with the id :  "+id));
	}

	@Override
	public void deleteStudent(Long id) {

		if(!studentRepository.existsById(id))
		{
			throw new StudentNotFoundException("Sorry, student not found..!");
		}
		studentRepository.deleteById(id);
	}
	
	private boolean studentAlreadyExists(String email) {
		return studentRepository.findByEmail(email).isPresent();
	}
	
	//for pagination
    @Override
    public Page<Student> getAllProducts(int page, int size) {
        PageRequest pageable = PageRequest.of(page, size);
        return studentRepository.findAll(pageable);
    }
    
    


}
