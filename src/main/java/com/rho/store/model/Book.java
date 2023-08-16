package com.rho.store.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="Books")
public class Book extends Product{
	private String autor;
	private int pages;
	public Book() {}
	public Book(String autor, int pages) {
		this.autor = autor;
		this.pages = pages;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public int getPages() {
		return pages;
	}
	public void setPages(int pages) {
		this.pages = pages;
	}
}
