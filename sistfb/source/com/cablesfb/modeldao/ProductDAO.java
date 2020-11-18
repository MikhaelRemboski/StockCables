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

//	public static void main(String[] args) {
//		try {
//			ProductDAO pdao = new ProductDAO();
//			Product p = new Product();
//			ResultSet rs2 = pdao.searchAll();
//			while (rs2.next()) {
//
//				p.setId(rs2.getInt("id"));
//				p.setName(rs2.getString("name"));
//				p.setPrice(rs2.getDouble("price"));
//				p.setSku(rs2.getInt("sku"));
//				p.setUnitys(rs2.getDouble("unitys"));
//				p.setType(rs2.getString("type"));
//				p.setMetersByType(rs2.getDouble("metersbytype"));
//				p.setDisponibleMeters(rs2.getDouble("disponiblemeters"));
//				
//				System.out.println(p.getName());
//			}
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
//
//	}
}
