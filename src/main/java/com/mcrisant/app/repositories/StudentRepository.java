package com.mcrisant.app.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.mcrisant.app.models.Student;

public interface StudentRepository extends ReactiveMongoRepository<Student, String> {

}
