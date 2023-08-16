package com.rho.store.test;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import com.rho.store.dao.CategoryDao;
import com.rho.store.dao.ClientDao;
import com.rho.store.dao.PedidoDao;
import com.rho.store.dao.ProductDao;
import com.rho.store.model.Category;
import com.rho.store.model.Client;
import com.rho.store.model.ItemsOrder;
import com.rho.store.model.Pedido;
import com.rho.store.model.Product;
import com.rho.store.utils.JPAUtils;
import com.rho.store.vo.sellRelation;

public class PedidoRegistry {

	public static void main(String[] args) {
		registerProduct();
		EntityManager em = JPAUtils.getEntityManager();
		ProductDao productDao = new ProductDao(em);
		Product product = productDao.queryById(1l);
		
		PedidoDao pedidoDao = new PedidoDao(em);
		ClientDao clientDao = new ClientDao(em);
		
		Client client = new Client("Juan", "egfege45");
		Pedido pedido = new Pedido(client);
		pedido.addItems(new ItemsOrder(5, product, pedido)); 
		em.getTransaction().begin();
		clientDao.saved(client);
		pedidoDao.saved(pedido);
		em.getTransaction().commit();
		
		BigDecimal totalValue = pedidoDao.totalValueSell();
		System.out.println("Valor total: " + totalValue);
		
		List<sellRelation> relation = pedidoDao.sellRelationVO();
		relation.forEach(System.out::println);

	}

	private static void registerProduct() {
		Category phones = new Category("PHONES"); 
		Product phone = new Product(
				"Iphone 11", 
				"Iphone 11 128gb", 
				new BigDecimal("10000"),
				phones
				);
		EntityManager em = JPAUtils.getEntityManager();
		ProductDao productDao = new ProductDao(em);
		CategoryDao categoryDao = new CategoryDao(em);

		
		em.getTransaction().begin();
		
		categoryDao.saved(phones);
		productDao.saved(phone);
		
		em.getTransaction().commit();
		em.close();
	}

}
