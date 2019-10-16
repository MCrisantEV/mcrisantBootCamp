package com.mcrisant.app.repositories;

import java.util.Date;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.mcrisant.app.models.Student;

import reactor.core.publisher.Mono;

public interface StudentRepository extends ReactiveMongoRepository<Student, String> {
	
	@Query("{ 'numDoc' : { $regex: ?0 } }")
    Mono<Student> findByNumDoc(String numero);
	
	@Query("{ 'nombres' : { $regex: ?0 } }")
    Mono<Student> findByNombres(String nombres);
	
	
	@Query("{'fechaNac' : ?0}")
	Mono<Student> findByFecha(Date fecha);
	
	/*@Query("{ 'age' : { $gt: ?0, $lt: ?1 } }")  @Query("{ 'names' : ?0}")
After -> despues GT
Before -> antes LT
	List<User> findUsersByAgeBetween(int ageGT, int ageLT);*/
}
