package com.rho.store.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="electronicos")
public class Electronics extends Product{
	private String brand;
	private String model;
	
	public Electronics() {}
	public Electronics(String brand, String model) {
		this.brand = brand;
		this.model = model;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
}
