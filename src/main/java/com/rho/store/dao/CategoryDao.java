package com.rho.store.dao;

import javax.persistence.EntityManager;

import com.rho.store.model.Category;

public class CategoryDao {
	private EntityManager em;

	public CategoryDao(EntityManager em) {
		this.em = em;
	}
	
	public void saved(Category category) {
		this.em.persist(category);
	}
	
	public void update(Category category) {
		this.em.merge(category);
	}
	
	public void remove(Category category) {
		category = this.em.merge(category);
		this.em.remove(category);
	}
}
