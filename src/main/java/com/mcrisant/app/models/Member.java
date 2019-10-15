package com.mcrisant.app.models;


import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "members")
public class Member extends Person {

	private String parentesco;

	public Member() {
	}

	public String getParentesco() {
		return parentesco;
	}

	public void setParentesco(String parentesco) {
		this.parentesco = parentesco;
	}

}
