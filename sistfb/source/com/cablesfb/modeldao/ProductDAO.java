package com.cablesfb.modeldao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.cablesfb.helper.Connector;
import com.cablesfb.model.Product;

public class ProductDAO {
	public List<Product> searchByOpt(String str) {
		List<Product> list = new ArrayList<Product>();
		try {
			Connector con = new Connector();
			Connection cn = con.getStockConnection();
			String sql = "SELECT *  FROM `stock` WHERE `name` LIKE '%" + str + "%' ORDER BY `id`  ASC";
			PreparedStatement ps = cn.prepareStatement(sql);
		
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Product p = new Product();

				p.setId(rs.getInt("id"));
				p.setName(rs.getString("name"));
				p.setPrice(rs.getDouble("price"));
				p.setSku(rs.getInt("sku"));
				p.setUnitys(rs.getDouble("unitys"));
				p.setType(rs.getString("type"));
				p.setMetersByType(rs.getDouble("metersbytype"));
				p.setDisponibleMeters(10);
				p.setDiscountType(rs.getString("discounttype"));
				list.add(p);
				p = list.get(0);
				System.out.println(p.getId());
			}
			return list;
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

	public void update(Product p) {
		try {
			Connector con = new Connector();
			Connection cn = con.getStockConnection();
			String sql = "INSERT INTO stock (`name`, `type`, `metersbytype`, `unitys`, `disponiblemeters`, `price`, `sku`, `discounttype`) VALUES(?,?,?,?,?,?,?,?)";
			PreparedStatement ps = cn.prepareStatement(sql);
			ps.setString(1, p.getName());
			ps.setString(2, p.getType());
			ps.setDouble(3, p.getMetersByType());
			ps.setDouble(4, p.getUnitys());

			ps.setDouble(5, p.getDisponibleMeters());
			ps.setDouble(6, p.getPrice());

			ps.setInt(7, p.getSku());
			System.out.println("hasta aca va bien");

			ps.setString(8, p.getDiscountType());
			System.out.println("hasta aca va bien");

			ps.executeUpdate();
			System.out.println("Se han cargado los datos a la base de datos del prroducto " + p.getName());
		} catch (Exception ex) {
			System.out.println("No se han podido cargar los datos a la base de datos del producto " + p.getName());
			ex.getMessage();
		}
	}

	public static void main(String[] args) {
		Product p = new Product();
		ProductDAO pdao = new ProductDAO();
		List<Product> lista = new ArrayList<Product>();

	
		
		
		lista = pdao.searchByOpt("un");
		for (int i = 0; i < lista.size(); i++) {
			p = lista.get(i);
			System.out.println(p.getId());
		}

	}
}
