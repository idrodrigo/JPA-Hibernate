package com.rho.store.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class PersonalData implements Serializable{
	private static final long serialVersionUID = 8063180201812979106L;
    private String name;
    private String dni;
    
	public PersonalData() {}
	public PersonalData(String name, String dni) {
		this.name = name;
		this.dni = dni;
	}
	public String getName() {
		return name;
	}
	public void setName(String nombre) {
		this.name = nombre;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
