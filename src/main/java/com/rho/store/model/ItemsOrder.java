package com.rho.store.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="items_order")
public class ItemsOrder {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private int quantity;
	private BigDecimal unitPrice;
	@ManyToOne(fetch=FetchType.LAZY)
	private Product product;
	@ManyToOne(fetch=FetchType.LAZY)
	private Pedido pedido;
	
	public ItemsOrder() {}
	
	public ItemsOrder(int quantity, Product product, Pedido pedido) {
		this.quantity = quantity;
		this.product = product;
		this.pedido = pedido;
		this.unitPrice= product.getPrice();
	}

	public Long getId() {
		return id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setOrder(Pedido order) {
		this.pedido = order;
	}
	public BigDecimal getValue() {
		return this.unitPrice.multiply(new BigDecimal(this.quantity));
	}
	
	
}
