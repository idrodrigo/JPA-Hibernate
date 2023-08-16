package com.rho.store.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import com.rho.store.model.Pedido;
import com.rho.store.vo.sellRelation;

public class PedidoDao {
	private EntityManager em;

	public PedidoDao(EntityManager em) {
		this.em = em;
	}
	
	public void saved(Pedido pedido) {
		this.em.persist(pedido);
	}
	
	public Pedido queryById(Long id) {
		return em.find(Pedido.class, id);
	}
	
	public List<Pedido> queryAllProducts(){
		String jqpl = "SELECT P FROM Pedido AS P";
		return em.createQuery(jqpl, Pedido.class).getResultList();
	}
	
	public BigDecimal totalValueSell() {
		String jqpl = "SELECT SUM(p.totalValue) FROM Pedido p";
		return em.createQuery(jqpl, BigDecimal.class).getSingleResult();
	}
	
	public List<sellRelation> sellRelationVO(){
		String jpql = "SELECT new com.rho.store.vo.sellRelation(product.name,"
				+ "SUM(item.quantity),"
				+ "MAX(pedido.date)) " 
				+ "FROM Pedido pedido "
				+ "JOIN pedido.items item "
				+ "JOIN item.product product "
				+ "GROUP BY product.name "
				+ "ORDER BY item.quantity DESC";
		return em.createQuery(jpql, sellRelation.class).getResultList();
	}
	
	public Pedido queryByClient(Long id) {
		String jpql = "SELECT p FROM Pedido p JOIN FETCH p.client WHERE p.id=:id";
		return em.createQuery(jpql, Pedido.class).setParameter("id", id).getSingleResult();
	}
}
