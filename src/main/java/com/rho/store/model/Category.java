package com.rho.store.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity 
@Table(name = "category")
public class Category {
	
	@EmbeddedId
    private CategoryId categoryId;
	
	public Category() {}
	public Category(String name) {
		this.categoryId = new CategoryId(name, "1234");
	}
	public String getName() {
		return categoryId.getName();
	}
	public void setName(String name) {
		this.categoryId.setName(name);
	}
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Long id;
//	private String name;
//	
//	
//	public Category() {
//	}
//
//	public Category(String name) {
//		this.name = name;
//	}
//	
//	public Long getId() {
//		return id;
//	}
//	public void setId(Long id) {
//		this.id = id;
//	}
//	public String getName() {
//		return name;
//	}
//	public void setName(String name) {
//		this.name = name;
//	}
}
