package com.enrico.microservices.auth.entity;

import javax.persistence.Entity;

@Entity
public class Permission extends BaseIdEntity {

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private static final long serialVersionUID = 1L;

	private String name;

}
