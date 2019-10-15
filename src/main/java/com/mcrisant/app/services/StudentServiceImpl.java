package com.mcrisant.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mcrisant.app.models.Student;
import com.mcrisant.app.repositories.StudentRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class StudentServiceImpl implements StudentService {
	
	@Autowired
	private StudentRepository studentRep;

	@Override
	public Flux<Student> findAll() {
		return studentRep.findAll();
	}

	@Override
	public Mono<Student> findById(String id) {
		return studentRep.findById(id);
	}

	@Override
	public Mono<Student> save(Student student) {
		return studentRep.save(student);
	}

	@Override
	public Mono<Void> delete(Student student) {
		return studentRep.delete(student);
	}

}
