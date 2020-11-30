package com.cablesfb.helper;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connector {
	Connection con;
	public Connection getLoginConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");			
			con = DriverManager.getConnection("jdbc:mysql://66.97.47.52:3306/registro","remboski","siemprefue20");
			System.out.println("Conectado correctamente");
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			System.out.println("el error de mierda");
		}
		return con;
	}
	public Connection getStockConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://66.97.47.52:3306/stock","remboski","siemprefue20");
			System.out.println("Conectado correctamente");
		} catch (Exception ex) {
			ex.getMessage();
			System.out.println("el error de mierda");
		}
		
		return con;
		
	}
	public Connection getClientConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://66.97.47.52:3306/client","remboski","siemprefue20");
			System.out.println("Conectado correctamente");
		} catch (Exception ex) {
			ex.getMessage();
			System.out.println("el error de mierda");
		}
		
		return con;
		
	}
	
	public Connection getOrderConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://66.97.47.52:3306/orders","remboski","siemprefue20");
			System.out.println("Conectado correctamente");
		} catch (Exception ex) {
			ex.getMessage();
			System.out.println("el error de mierda");
		}
		
		return con;
		
	}

}
