package com.rho.store.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

public class LoadRecords {

	public static void cargarRegistros() throws FileNotFoundException {
		EntityManager em = JPAUtils.getEntityManager();
		CategoryDao categoryDao = new CategoryDao(em);
		ProductDao productDao = new ProductDao(em);
		ClientDao clientDao = new ClientDao(em);
		PedidoDao pedidoDao = new PedidoDao(em);
		em.getTransaction().begin();
		
		loadCategory("category",categoryDao,em);
		
		loadProduct("product",productDao,categoryDao,em);
		
		loadClient("client",clientDao,em);
		
		List<Client> clientsList = clientDao.queryAllProducts();
		List<Pedido> pedidoList= new ArrayList<>();
		
		for(Client cl:clientsList) {
			pedidoList.add(new Pedido(cl));
		}
		
		for(int i=0;i<pedidoList.size();i++) {
			pedidoList.get(i).addItems(
					new ItemsOrder(i+1,productDao.queryById((long) (i+1)),pedidoList.get(i)));
			

			pedidoDao.saved(pedidoList.get(i));
		}
		
		em.getTransaction().commit();
		em.close();
		
	}
	
	private static void loadProduct(String type, ProductDao productDao,CategoryDao categoryDao, EntityManager em) throws FileNotFoundException {
		List<String> productsTxt =readFile(type);
		for(int i=0;i<productsTxt.size();i++) {
			String[] line = productsTxt.get(i).split(";");
			if(line.length>1) {
				Category category=categoryDao.queryByName(line[3]);
				Product product = new Product(line[4],line[0],new BigDecimal(line[1]),category);
				productDao.saved(product);
				em.flush();
			}
		}
	}

	private static void loadCategory(String type, CategoryDao categoryDao,EntityManager em) throws FileNotFoundException {
		List<String> categorysTxt =readFile(type);		
		for(int i=0;i<categorysTxt.size();i++) {
			String[] line = categorysTxt.get(i).split(";");
			if(line.length==1) {
				Category category = new Category(categorysTxt.get(i));
				categoryDao.saved(category);
				em.flush();	
			}
		}
	}

	private static void loadClient(String type, ClientDao clientDao,EntityManager em) throws FileNotFoundException {
		List<String> clientsTxt =readFile(type);		
		for(int i=0;i<clientsTxt.size();i++) {
			String[] line = clientsTxt.get(i).split("~");
			System.out.println(line[0]+line[1]);
			if(line.length>1) {
				Client client= new Client(line[0],line[1]);
				clientDao.saved(client);
				em.flush();	
			}
		}
	}
	
	private static List<String> readFile(String type) throws FileNotFoundException {
		File file = new File("C:\\Users\\idrod\\java\\store\\src\\main\\java\\com\\rho\\store\\utils\\"+type+".txt");
		
		Scanner scan = new Scanner(file);
		List<String> pedido= new ArrayList<>();
		while(scan.hasNextLine()){
			pedido.add(scan.nextLine());
		}
		scan.close();
		return pedido;
	}

}
