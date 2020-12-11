package com.cablesfb.model;

public class Product {
	private int id;
	private String name;
	private String type;
	private double metersByType;
	private double unitys;
	private double disponibleMeters;
	private double price;
	private int sku;
	private String discountType;
	
	public Product() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getMetersByType() {
		return metersByType;
	}

	public void setMetersByType(double metersByType) {
		this.metersByType = metersByType;
	}

	public double getUnitys() {
		return unitys;
	}

	public void setUnitys(double unitys) {
		this.unitys = unitys;
	}

	public double getDisponibleMeters() {
		return disponibleMeters;
	}

	public void setDisponibleMeters(double disponibleMeters) {
		this.disponibleMeters = disponibleMeters;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getSku() {
		return sku;
	}

	public void setSku(int sku) {
		this.sku = sku;
	}

	public String getDiscountType() {
		return discountType;
	}

	public void setDiscountType(String discountType) {
		this.discountType = discountType;
	}

	@Override
	public String toString() {
		return "nombre=" + name + ", tipo=" + type + ", Metros por tipo=" + metersByType
				+ ", unidades=" + unitys + ", precio=" + price + ", sku=" + sku
				+ ", Tipo de descuento=" + discountType;
	}
	
}
