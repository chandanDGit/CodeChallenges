package com.store.management.model;

import com.store.management.utility.Constants;

public class Product {

	private String productName;

	private Double buyingPrice;

	private Double sellingPrice;

	private long availableQuantity;

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Double getBuyingPrice() {
		return buyingPrice;
	}

	public void setBuyingPrice(Double buyingPrice) {
		this.buyingPrice = buyingPrice;
	}

	public Double getSellingPrice() {
		return sellingPrice;
	}

	public void setSellingPrice(Double sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

	public long getAvailableQuantity() {
		return availableQuantity;
	}

	public void setAvailableQuantity(long availableQuantity) {
		this.availableQuantity = availableQuantity;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getProductName()).append(Constants._tab).append(getBuyingPrice()).append(Constants._tab)
				.append(getSellingPrice()).append(Constants._tab).append(getAvailableQuantity()).append(Constants._tab);
		return sb.toString();
	}

}
