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
			str = "%" + str + "%";
			Connector con = new Connector();
			Connection cn = con.getStockConnection();
			String sql = "SELECT *  FROM `stock` WHERE `name` LIKE ? OR `sku` LIKE ?  OR `id` LIKE ? ORDER BY `id`  ASC";
			PreparedStatement ps = cn.prepareStatement(sql);
			ps.setString(1, str);
			ps.setString(2, str);
			ps.setString(3, str);
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
			ps.close();
			cn.close();
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

	
	public Product searchById(int id) {
		try {
			Connector con = new Connector();
			Connection cn = con.getStockConnection();
			String sql = "SELECT *  FROM `stock` WHERE `id` = ?";
			PreparedStatement ps = cn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			Product p = new Product();
			
			
			while (rs.next()) {
				p.setId(rs.getInt("id"));
				p.setName(rs.getString("name"));
				p.setPrice(rs.getDouble("price"));
				p.setSku(rs.getInt("sku"));
				p.setUnitys(rs.getDouble("unitys"));
				p.setType(rs.getString("type"));
				p.setMetersByType(rs.getDouble("metersbytype"));
				p.setDisponibleMeters(10);
				p.setDiscountType(rs.getString("discounttype"));
			}
			ps.close();
			cn.close();
			return p;
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			System.out.println("Siempre la cagas mikhael");
			return null;
		}
	}

	
	public void insert(Product p) {
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

			ps.setString(8, p.getDiscountType());

			ps.executeUpdate();
			ps.close();
			cn.close();
			System.out.println("Se han cargado los datos a la base de datos del prroducto " + p.getName());
		} catch (Exception ex) {
			System.out.println("No se han podido cargar los datos a la base de datos del producto " + p.getName());
			ex.getMessage();
		}
	}

	public void delete(int id) {
		try {
			Connector con = new Connector();
			Connection cn = con.getStockConnection();
			String sql = "DELETE FROM stock WHERE id = ?";
			PreparedStatement ps = cn.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
			ps.close();
			cn.close();
			System.out.println("Producto eliminado con exito");

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			System.out.println("El producto con id " + id + " no pudo ser eliminado con exito");
		}
	}
	public void modifyById (Product p) {
		try {
			Connector con = new Connector();
			Connection cn = con.getStockConnection();			
			String sql = "UPDATE stock  SET `name` = ?, `type` = ?, "
					+ "`metersbytype` = ?, `unitys` = ?,"
					+ " `price` = ?, `sku` = ?, `discounttype`= ? WHERE `id` = ? ";
			PreparedStatement ps = cn.prepareStatement(sql);
			ps.setString(1, p.getName());
			ps.setString(2, p.getType());
			ps.setDouble(3, p.getMetersByType());
			ps.setDouble(4, p.getUnitys());
			ps.setDouble(5, p.getPrice());
			ps.setInt(6, p.getSku());
			ps.setString(7, p.getDiscountType());
			ps.setInt(8, p.getId());
			ps.executeUpdate();
			ps.close();
			cn.close();
			System.out.println("Modificacion exitosa");
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			System.out.println("error");
		}
	}
}
