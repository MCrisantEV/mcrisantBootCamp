package com.mcrisant.app.services;


import java.util.Date;

import com.mcrisant.app.models.Student;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StudentService {
	
	public Flux<Student> findAll();
	public Mono<Student> findById(String id);
	public Mono<Student> save(Student student);
	public Mono<Void> delete(Student student);
	public Flux<Student> findByNombres(String nombres);
	public Mono<Student> findByNumDoc(String numero);
	public Flux<Student> findByFecha(Date fecha);
	public Flux<Student> findByRangoFechas(Date fechaInicio, Date fechaFin);

}
