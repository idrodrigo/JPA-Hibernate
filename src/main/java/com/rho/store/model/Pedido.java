package com.rho.store.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity 
@Table(name = "pedido")
public class Pedido {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private LocalDate date = LocalDate.now();
	private BigDecimal totalValue = new BigDecimal(0);
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Client client;
	
	@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
	private List<ItemsOrder> items = new ArrayList<>();
	
	public Pedido() {}
	
	public Pedido(Client client) {
		this.client = client;
	}
	
	public void addItems(ItemsOrder item) {
		item.setOrder(this);
		this.items.add(item);
		this.totalValue= this.totalValue.add(item.getValue());
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public BigDecimal getTotalValue() {
		return totalValue;
	}

	public void setTotalValue(BigDecimal totalValue) {
		this.totalValue = totalValue;
	}

	public List<ItemsOrder> getItems() {
		return items;
	}

	public void setItems(List<ItemsOrder> items) {
		this.items = items;
	}
	
	

}
