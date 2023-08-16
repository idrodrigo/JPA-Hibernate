package com.rho.store.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import com.rho.store.model.Client;

public class ClientDao {
	private EntityManager em;

	public ClientDao(EntityManager em) {
		this.em = em;
	}
	
	public void saved(Client client) {
		this.em.persist(client);
	}
	
	public Client queryById(Long id) {
		return em.find(Client.class, id);
	}
	
	public List<Client> queryAllProducts(){
		String jqpl = "SELECT P FROM Client AS P";
		return em.createQuery(jqpl, Client.class).getResultList();
	}
	
	public List<Client> queryByName(String name){
		String jqpl = "SELECT P FROM Client AS P WHERE P.name=:name";
		return em.createQuery(jqpl, Client.class).setParameter("name", name).getResultList();
	}
	
	public List<Client> queryByCategory(String nameCategory){
		String jqpl = "SELECT P FROM Client AS P WHERE P.category.name=:nameCategory";
		return  em.createQuery(jqpl, Client.class).setParameter("nameCategory", nameCategory).getResultList();
	}
	
	public BigDecimal queryPriceBYName(String name) {
		String jqpl = "SELECT P.price FROM Client AS P WHERE P.name = :name";
		return em.createQuery(jqpl, BigDecimal.class).setParameter("name", name).getSingleResult();
	}
}
