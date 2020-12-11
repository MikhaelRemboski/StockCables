package com.cablesfb.model;

public class Client {
	private int id;
	private String name;
	private long cuit;
	private String adress;
	private String email;
	private String transport;

	
	
	public Client() {}


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

	public long getCuit() {
		return cuit;
	}

	public void setCuit(long cuit) {
		this.cuit = cuit;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTransport() {
		return transport;
	}

	public void setTransport(String transport) {
		this.transport = transport;
	}


	@Override
	public String toString() {
		return " nombre=" + name + ", cuit=" + cuit + ", direccion=" + adress + ", email=" + email + ", transporte="
				+ transport;
	}
	

}
