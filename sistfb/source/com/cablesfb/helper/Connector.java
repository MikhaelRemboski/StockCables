package com.cablesfb.helper;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connector {
	Connection con;
	public Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/registro","root","");
			System.out.println("Conectado correctamente");
		} catch (Exception ex) {
			ex.getMessage();
			System.out.println("el error de mierda");
		}
		return con;
	}
	
}
