package com.rho.store.model;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity 
@Table(name = "clients")
public class Client {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
//	private String name;
//	private String dni;
	@Embedded
	private PersonalData personalData;
	
	public Client() {
	}

	public Client(String name, String dni) {
		this.personalData = new PersonalData(name, dni);
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return personalData.getName();
	}

	public void setName(String name) {
		this.personalData.setName(name);
	}

	public String getDni() {
		return this.personalData.getDni();
	}

	public void setDni(String dni) {
		this.personalData.setDni(dni);
	}


}
