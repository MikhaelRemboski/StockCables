package com.cablesfb.model;

public class Order {

	private int id;
	private long orderId;
	private  int clientId;
	private int productId;
	private int unitys;
	private String productDescription;
	private String state;
	
	
	public Order() {
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public long getOrderId() {
		return orderId;
	}
	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}
	public int getClientId() {
		return clientId;
	}
	public void setClientId(int clientId) {
		this.clientId = clientId;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getUnitys() {
		return unitys;
	}
	public void setUnitys(int unitys) {
		this.unitys = unitys;
	}
	public String getProductDescription() {
		return productDescription;
	}
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	public String isState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
}
