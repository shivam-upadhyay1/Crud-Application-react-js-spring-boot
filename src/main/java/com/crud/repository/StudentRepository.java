package com.crud.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crud.model.Student;
@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{

	public Optional<Student> findByEmail(String email);
	
    Page<Student> findAll(Pageable pageable);

}
