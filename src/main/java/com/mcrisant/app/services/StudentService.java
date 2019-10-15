package com.mcrisant.app.services;


import com.mcrisant.app.models.Student;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StudentService {
	
	public Flux<Student> findAll();
	public Mono<Student> findById(String id);
	public Mono<Student> save(Student student);
	public Mono<Void> delete(Student student);

}
