package com.mcrisant.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mcrisant.app.models.Estudiante;
import com.mcrisant.app.repositories.EstudianteRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/estudiantes")
public class EstudianteController {
	@Autowired
	private EstudianteRepository rep;
	
	@GetMapping()
	public Flux<Estudiante> show(){
		Flux<Estudiante> estudiante = rep.findAll().map(estudiantes -> {
			estudiantes.setNombres(estudiantes.getNombres());
			return estudiantes;
		});
		
		return estudiante;
	}
	
	@GetMapping("/{id}")
	public Mono<Estudiante> show(@PathVariable String id){
		Mono<Estudiante> estudiante = rep.findById(id);
		
		return estudiante;
	}
}
