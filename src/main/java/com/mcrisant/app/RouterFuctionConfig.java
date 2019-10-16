package com.mcrisant.app;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import java.text.ParseException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.mcrisant.app.handler.StudentHandler;


@Configuration
public class RouterFuctionConfig {
	
	@Bean
	public RouterFunction<ServerResponse> routesStudent(StudentHandler handler){
		return route(GET("/student"), handler::listar)
				.andRoute(GET("/student/{id}"), handler::ver)
				.andRoute(POST("/student"), handler::crear)
				.andRoute(PUT("/student/{id}"), handler::editar)
				.andRoute(DELETE("/student/{id}"), handler::eliminar)
				.andRoute(GET("/student/names/{nombres}"), handler::listByNombres)
				.andRoute(GET("/student/numero/{numDoc}"), handler::listByNumDoc)
				.andRoute(GET("/student/fecha/{fechaNac}"), request -> {
					try {
						return handler.listByfecha(request);
					} catch (ParseException e) {
						e.printStackTrace();
					}
					return null;
				});
	}
}
