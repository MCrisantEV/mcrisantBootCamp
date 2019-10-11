package com.mcrisant.app.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.mcrisant.app.models.Estudiante;

public interface EstudianteRepository extends ReactiveMongoRepository<Estudiante, String> {

}
