package com.rho.store.test;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import com.rho.store.dao.CategoryDao;
import com.rho.store.dao.ProductDao;
import com.rho.store.model.Category;
import com.rho.store.model.Product;
import com.rho.store.utils.JPAUtils;

public class ParametersTest {

	public static void main(String[] args) {
		cargarBancoDeDatos();

//        EntityManager em = JPAUtils.getEntityManager();
//        ProductDao productoDao =new ProductDao(em);
//
//        List<Product> resultado = productoDao.consultarPorParametros("FIFA", new BigDecimal(10000), null);
//
//        System.out.println(resultado.get(0).getDescripcion());

		EntityManager em = JPAUtils.getEntityManager();
        ProductDao productoDao =new ProductDao(em);

        List<Product> resultado = productoDao.queryByParametersWithAPICriteria("X", null, null);

        System.out.println(resultado.get(0).getDescription());


	}
	private static void cargarBancoDeDatos() {
        Category celulares = new Category("CELULARES");
        Category videoJuegos = new Category("VIDEO_JUEGOS");
        Category electronicos = new Category("ELECTRONICOS");

        Product celular = new Product("X", "producto nuevo", new BigDecimal(10000), celulares);
        Product videoJuego = new Product("FIFA", "2000", new BigDecimal(10000), videoJuegos);
        Product memoria = new Product("memoria ram", "30 GB", new BigDecimal(10000), electronicos);

        EntityManager em = JPAUtils.getEntityManager();
        ProductDao productoDao = new ProductDao(em);
        CategoryDao categoriaDao = new CategoryDao(em);

        em.getTransaction().begin();

        categoriaDao.saved(celulares);
        categoriaDao.saved(videoJuegos);
        categoriaDao.saved(electronicos);

        productoDao.saved(celular);
        productoDao.saved(videoJuego);
        productoDao.saved(memoria);

        em.getTransaction().commit();
        em.close();
    }
}
