package com.rho.store.test;

import java.io.FileNotFoundException;

import javax.persistence.EntityManager;

import com.rho.store.dao.PedidoDao;
import com.rho.store.model.Pedido;
import com.rho.store.utils.JPAUtils;

public class PerformanceTest {
	public static void main(String[] args) throws FileNotFoundException {
		LoadRecords.cargarRegistros();
		
		EntityManager em = JPAUtils.getEntityManager();
		
		PedidoDao pedidoDao = new PedidoDao(em);
		Pedido pedidoWithCLient = pedidoDao.queryByClient(1l);
		
		em.close();
		
//		System.out.println(pedido.getDate());
//		System.out.println(pedido.getItems().size());
		System.out.println(pedidoWithCLient.getClient().getName());
	}
}
