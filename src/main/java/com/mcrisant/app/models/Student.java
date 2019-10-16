package com.mcrisant.app.models;


import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "student")
public class Student extends Person{
	@Id
	private String id;
	
	@Valid
	@NotNull
	private List<Member> familia;

	public Student() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<Member> getFamilia() {
		return familia;
	}

	public void setFamilia(List<Member> familia) {
		this.familia = familia;
	}
		
}
