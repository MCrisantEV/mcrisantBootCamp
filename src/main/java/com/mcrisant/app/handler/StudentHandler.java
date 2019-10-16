package com.mcrisant.app.handler;

import static org.springframework.web.reactive.function.BodyInserters.fromObject;

import java.net.URI;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.mcrisant.app.models.Student;
import com.mcrisant.app.services.StudentService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class StudentHandler {
	
	@Autowired
	private StudentService service;
	
	@Autowired
	private Validator validator;
	
	public Mono<ServerResponse> listar(ServerRequest request) {
		return ServerResponse.ok().contentType(MediaType.APPLICATION_PROBLEM_JSON_UTF8).body(service.findAll(),
				Student.class);
	}

	public Mono<ServerResponse> ver(ServerRequest request) {
		String id = request.pathVariable("id");
		return service.findById(id)
				.flatMap(s -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON_UTF8).body(fromObject(s)))
				.switchIfEmpty(ServerResponse.notFound().build());
	}
	
	public Mono<ServerResponse> listByNombres(ServerRequest request) {
		String nom = request.pathVariable("nombres");
		return service.findByNombres(nom)
				.flatMap(s -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON_UTF8).body(fromObject(s)))
				.switchIfEmpty(ServerResponse.notFound().build());
	}
	
	public Mono<ServerResponse> listByNumDoc(ServerRequest request) {
		String numero = request.pathVariable("numDoc");
		return service.findByNumDoc(numero)
				.flatMap(s -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON_UTF8).body(fromObject(s)))
				.switchIfEmpty(ServerResponse.notFound().build());
	}
	
	public Mono<ServerResponse> listByfecha(ServerRequest request) throws ParseException {	
		DateFormat format = new SimpleDateFormat();
		String fechaString = request.pathVariable("fechaNac");
	
		 try {
			 Date fecha = format.parse(fechaString);
			 return service.findByFecha(fecha)
						.flatMap(s -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON_UTF8).body(fromObject(s)))
						.switchIfEmpty(ServerResponse.notFound().build());

	        } catch (ParseException e) {
	            return ServerResponse.notFound().build();
	        }
	}
	
	public Mono<ServerResponse> crear(ServerRequest request) {
		Mono<Student> student = request.bodyToMono(Student.class);
		return student.flatMap(s -> {

			Errors errors = new BeanPropertyBindingResult(s, Student.class.getName());
			validator.validate(s, errors);

			if (errors.hasFieldErrors()) {
				return Flux.fromIterable(errors.getFieldErrors())
						.map(fieldError -> "El Campo "+ fieldError.getField() + " " + fieldError.getDefaultMessage())
						.collectList()
						.flatMap(list -> ServerResponse.badRequest().body(fromObject(list)));
			} else {
				if(s.getFamilia().size() >= 2) {
					int contPadres = 0;
					for(int i=0; i<s.getFamilia().size(); i++) {
						if(s.getFamilia().get(i).getParentesco().equals("padre") || s.getFamilia().get(i).getParentesco().equals("madre")) {
							contPadres++;
						}
					}
					if(contPadres <= 2) {
						return service.save(s).flatMap(sdb -> ServerResponse.created(URI.create("student/".concat(sdb.getId())))
								.contentType(MediaType.APPLICATION_JSON_UTF8).body(fromObject(sdb)));
					}else {
						//mas de 2 Padres
						return ServerResponse.badRequest().build();
					}
					
				} else {
					//menos de dos familiares
					return ServerResponse.badRequest().build();
				}
			}
		});
	}

	public Mono<ServerResponse> editar(ServerRequest request) {
		Mono<Student> student = request.bodyToMono(Student.class);
		String id = request.pathVariable("id");

		Mono<Student> studentDb = service.findById(id);

		return studentDb.zipWith(student, (db, req) -> {
			db.setNombres(req.getNombres());
			db.setApellidos(req.getApellidos());
			db.setGenero(req.getGenero());
			db.setTipoDoc(req.getTipoDoc());
			db.setNumDoc(req.getNumDoc());
			db.setFechaNac(req.getFechaNac());
			return db;
		}).flatMap(s -> ServerResponse.created(URI.create("student/".concat(s.getId())))
				.contentType(MediaType.APPLICATION_JSON_UTF8).body(service.save(s), Student.class))
				.switchIfEmpty(ServerResponse.notFound().build());
	}

	public Mono<ServerResponse> eliminar(ServerRequest request) {
		String id = request.pathVariable("id");
		Mono<Student> studentDb = service.findById(id);

		return studentDb.flatMap(s -> service.delete(s).then(ServerResponse.noContent().build()))
				.switchIfEmpty(ServerResponse.notFound().build());
	}

}
