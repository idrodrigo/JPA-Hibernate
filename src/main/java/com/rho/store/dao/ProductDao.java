package com.rho.store.dao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

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
		return em.createNamedQuery("product.queryByName", BigDecimal.class).setParameter("name", name).getSingleResult();
	}
	
//	public List<Product> consultarPorParametros(String nombre, BigDecimal precio,LocalDate fecha){
//	    StringBuilder jpql=new StringBuilder("SELECT p FROM Product p WHERE 1=1 ");
//
//	    if(nombre!=null && !nombre.trim().isEmpty()) {
//	        jpql.append("AND p.nombre=:nombre ");
//	    }
//	    if(precio!=null && !precio.equals(new BigDecimal(0))) {
//	        jpql.append("AND p.precio=:precio ");
//	    }
//	    if(fecha!=null) {
//	        jpql.append("AND p.fechaDeRegistro=:fecha");
//	    }
//	    TypedQuery<Product> query = em.createQuery(jpql.toString(),Product.class);
//	    if(nombre!=null && !nombre.trim().isEmpty()) {
//	        query.setParameter("nombre", nombre);
//	    }
//	    if(precio!=null && !precio.equals(new BigDecimal(0))) {
//	        query.setParameter("precio", precio);
//	    }
//	    if(fecha!=null) {
//	        query.setParameter("fechaDeRegistro", fecha);
//	    }
//	    return query.getResultList();        
//	}
	
	
	public List<Product> queryByParametersWithAPICriteria(String nombre, BigDecimal precio,LocalDate fecha){
	    CriteriaBuilder builder = em.getCriteriaBuilder();
	    CriteriaQuery<Product> query = builder.createQuery(Product.class);
	    Root<Product> from = query.from(Product.class);

	    Predicate filtro = builder.and();
	    if(nombre!=null && !nombre.trim().isEmpty()) {
	        filtro=builder.and(filtro,builder.equal(from.get("name"), nombre));
	    }
	    if(precio!=null && !precio.equals(new BigDecimal(0))) {
	        filtro=builder.and(filtro,builder.equal(from.get("price"), precio));
	    }
	    if(fecha!=null) {
	        filtro=builder.and(filtro,builder.equal(from.get("registryDate"), fecha));
	    }
	    query=query.where(filtro);
	    return em.createQuery(query).getResultList();
	}
}
