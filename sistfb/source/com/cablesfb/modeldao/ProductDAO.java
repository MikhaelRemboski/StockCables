package com.cablesfb.modeldao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


import com.cablesfb.helper.Connector;
import com.cablesfb.model.Product;

public class ProductDAO {
	public Product searchBySku(int sku) {
		try {
			Product p = new Product();
			Connector con = new Connector();
			Connection cn = con.getStockConnection();
			String sql = "SELECT * FROM 'stock' WHERE sku=?";
			PreparedStatement ps = cn.prepareStatement(sql);
			ps.setInt(1, sku);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				p.setId(rs.getInt("id"));
				p.setName(rs.getString("name"));
				p.setPrice(rs.getDouble("price"));
				p.setSku(rs.getInt("sku"));
				p.setUnitys(rs.getDouble("unitys"));
				p.setType(rs.getString("type"));
				p.setMetersByType(rs.getDouble("metersbytype"));
				p.setDisponibleMeters(rs.getDouble("disponiblemeters"));
				p.setDiscountType(rs.getString("discounttype"));
			}
			return p;
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			System.out.println("Siempre la cagas mikhael");
			return null;
		}

	}

	public ResultSet searchAll() {
		try {
			Connector con = new Connector();
			Connection cn = con.getStockConnection();
			String sql = "SELECT * FROM stock";
			PreparedStatement ps = cn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			return rs;
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			System.out.println("Siempre la cagas mikhael");
			return null;
		}

	}

	public String update (Product p) {
		String resultado = "";
		return resultado;
	}
}
