package com.rho.store.vo;

import java.time.LocalDate;

public class sellRelation {
	private String productName;
	private Long ProductQuantity;
	private LocalDate LastSellDate;
	public sellRelation(String productName, Long productQuantity, LocalDate lastSellDate) {
		this.productName = productName;
		ProductQuantity = productQuantity;
		LastSellDate = lastSellDate;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Long getProductQuantity() {
		return ProductQuantity;
	}
	public void setProductQuantity(Long productQuantity) {
		ProductQuantity = productQuantity;
	}
	public LocalDate getLastSellDate() {
		return LastSellDate;
	}
	public void setLastSellDate(LocalDate lastSellDate) {
		LastSellDate = lastSellDate;
	}
	@Override
	public String toString() {
		return "sellRelation [productName=" + productName + ", ProductQuantity=" + ProductQuantity + ", LastSellDate="
				+ LastSellDate + "]";
	}
	
	
}
