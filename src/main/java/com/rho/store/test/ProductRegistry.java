package com.rho.store.test;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import com.rho.store.dao.CategoryDao;
import com.rho.store.dao.ProductDao;
import com.rho.store.model.Category;
import com.rho.store.model.Product;
import com.rho.store.utils.JPAUtils;

public class ProductRegistry {

	public static void main(String[] args) {
//		Category phones = new Category("PHONES"); 
////		Product phone = new Product(
////				"Iphone", 
////				"Iphone 11 128gb", 
////				new BigDecimal("10000"),
////				phones
////				);
//		
//		EntityManager em = JPAUtils.getEntityManager();
//		
////		ProductDao productDao = new ProductDao(em);
////		CategoryDao categoryDao = new CategoryDao(em);
//		
//		em.getTransaction().begin();
//		
////		categoryDao.saved(phones);
////		productDao.saved(phone);
//		
//		em.persist(phones);
//		phones.setName("BOOKS");
//		
//		em.flush();
//		em.clear();
//		
//		phones = em.merge(phones);
//		phones.setName("TECH");
//		
//		em.flush();
//		em.clear();
//		
//		phones = em.merge(phones);
//		em.remove(phones);
//		em.flush();
//		em.getTransaction().commit();
//		em.close();
		
		registerProduct();
		EntityManager em = JPAUtils.getEntityManager();
		ProductDao productDao = new ProductDao(em);
		CategoryDao categoryDao = new CategoryDao(em);
		Product product = productDao.queryById(1L);
		System.out.println(product.getName());
		
		List<Product> allProducts = productDao.queryAllProducts();
		allProducts.forEach(p -> System.out.println(p.getDescription()));
		
		List<Product> filteredProducts = productDao.queryByName("Iphone 11");	
		for(Product p : filteredProducts) {
			System.out.println(p.getName());
		}
		
		List<Product> filteredProductsByCategory = productDao.queryByCategory("PHONES");
		filteredProductsByCategory.forEach(p->System.out.println(p.getName()));
		
		BigDecimal price = productDao.queryPriceBYName("Iphone 11");
		System.out.println(price);
		
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
