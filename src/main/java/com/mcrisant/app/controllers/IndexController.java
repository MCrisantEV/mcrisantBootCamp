package com.mcrisant.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.mcrisant.app.models.Estudiante;
import com.mcrisant.app.repositories.EstudianteRepository;

import reactor.core.publisher.Flux;


@Controller
public class IndexController {
	@Autowired
	private EstudianteRepository rep;
	
	@Autowired
	private ReactiveMongoTemplate mongoTemplate;
	
	@GetMapping({"/index","/"})
	public String index() {
		return "index";
	}
	
	@GetMapping("/insert")
	public String insert(){
		Flux.just(new Estudiante("Moises Jesus", "Crisanto Benites"), new Estudiante("Martha", "Sanchez Lara"),
				new Estudiante("Ana Milagros", "Zapata Anto"), new Estudiante("Jose", "Llano Ortega"))
				.flatMap(estudiante -> rep.save(estudiante)).subscribe();
		
		return "index";
	}
	
	@GetMapping("/delete")
	public String delete(){
		mongoTemplate.dropCollection("estudiantes").subscribe();
		
		return "index";
	}
	
	

}
