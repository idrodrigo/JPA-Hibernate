package com.rho.store.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import com.rho.store.model.Product;

public class ProductDao {
	private EntityManager em;

	public ProductDao(EntityManager em) {
		this.em = em;
	}
	
	public void saved(Product product) {
		this.em.persist(product);
	}
	
	public Product queryById(Long id) {
		return em.find(Product.class, id);
	}
	
	public List<Product> queryAllProducts(){
		String jqpl = "SELECT P FROM Product AS P";
		return em.createQuery(jqpl, Product.class).getResultList();
	}
	
	public List<Product> queryByName(String name){
		String jqpl = "SELECT P FROM Product AS P WHERE P.name=:name";
		return em.createQuery(jqpl, Product.class).setParameter("name", name).getResultList();
	}
	
	public List<Product> queryByCategory(String nameCategory){
		String jqpl = "SELECT P FROM Product AS P WHERE P.category.name=:nameCategory";
		return  em.createQuery(jqpl, Product.class).setParameter("nameCategory", nameCategory).getResultList();
	}
	
	public BigDecimal queryPriceBYName(String name) {
		String jqpl = "SELECT P.price FROM Product AS P WHERE P.name = :name";
		return em.createQuery(jqpl, BigDecimal.class).setParameter("name", name).getSingleResult();
	}
}
