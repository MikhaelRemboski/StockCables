package com.cablesfb.modeldao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.cablesfb.helper.Connector;
import com.cablesfb.model.Order;

public class OrderDAO {
	public List<Order> selectAll() {
		try {
			List<Order> list = new ArrayList<Order>();
			Connector con = new Connector();
			Connection cn = con.getOrderConnection();
			String sql = "SELECT * FROM orders";
			PreparedStatement ps = cn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Order o = new Order();
				o.setClientId(rs.getInt("idClient"));
				o.setId(rs.getInt("id"));
				o.setOrderId(rs.getLong("idOrder"));
				o.setProductDescription(rs.getString("productDescription"));
				o.setProductId(rs.getInt("idProduct"));
				o.setState(rs.getString("state"));
				o.setUnitys(rs.getInt("unitys"));
				list.add(o);
			}
			cn.close();
			ps.close();
			return list;
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return null;
	}

	public Order getMaxOrder() {
		try {
			Connector con = new Connector();
			Connection cn = con.getOrderConnection();
			String sql = "SELECT * FROM orders WHERE idOrder = (SELECT MAX(idOrder) FROM orders) ORDER BY idOrder DESC";
			PreparedStatement ps = cn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			Order o = new Order();
			while (rs.next()) {
				o.setClientId(rs.getInt("idClient"));
				o.setId(rs.getInt("id"));
				o.setOrderId(rs.getLong("idOrder"));
				o.setProductDescription(rs.getString("productDescription"));
				o.setProductId(rs.getInt("idProduct"));
				o.setState(rs.getString("state"));
				o.setUnitys(rs.getInt("unitys"));
			}
			cn.close();
			ps.close();
			return o;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	public void insertOrder(Order o) {
		try {
			Connector con = new Connector();
			Connection cn = con.getOrderConnection();
			String sql = "INSERT INTO `orders`(`idOrder`, `idClient`, `idProduct`, `unitys`, `productDescription`, `state`,`totalPrice`) "
					+ "VALUES(?,?,?,?,?,?,?)";
			PreparedStatement ps = cn.prepareStatement(sql);
			ps.setLong(1, o.getOrderId());
			ps.setInt(2, o.getClientId());
			ps.setInt(3, o.getProductId());
			ps.setInt(4, o.getUnitys());
			ps.setString(5, o.getProductDescription());
			ps.setString(6, o.getState());
			ps.setDouble(7, o.getTotalPrice());
			ps.executeUpdate();
			cn.close();
			ps.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public List<Order> selectNoRepeatOrderId() {
		try {
			List<Order> list = new ArrayList<Order>();
			Connector con = new Connector();
			Connection cn = con.getOrderConnection();
			String sql = "SELECT * FROM `orders` GROUP BY idOrder";
			PreparedStatement ps = cn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Order o = new Order();
				o.setClientId(rs.getInt("idClient"));
				o.setId(rs.getInt("id"));
				o.setOrderId(rs.getLong("idOrder"));
				o.setProductDescription(rs.getString("productDescription"));
				o.setProductId(rs.getInt("idProduct"));
				o.setState(rs.getString("state"));
				o.setUnitys(rs.getInt("unitys"));
				o.setTotalPrice(rs.getDouble("totalPrice"));
				list.add(o);
			}
			cn.close();
			ps.close();
			return list;
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return null;

	}

	public List<Order> selectAllByState() {
		try {
			List<Order> list = new ArrayList<Order>();
			Connector con = new Connector();
			Connection cn = con.getOrderConnection();
			String sql = "SELECT * FROM `orders` WHERE `state` =" + '"' + "reservado" + '"';
			PreparedStatement ps = cn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Order o = new Order();
				o.setClientId(rs.getInt("idClient"));
				o.setId(rs.getInt("id"));
				o.setOrderId(rs.getLong("idOrder"));
				o.setProductDescription(rs.getString("productDescription"));
				o.setProductId(rs.getInt("idProduct"));
				o.setState(rs.getString("state"));
				o.setUnitys(rs.getInt("unitys"));
				list.add(o);
			}
			cn.close();
			ps.close();
			return list;
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return null;
	}

	public void deleteByOrderId(Long id) {
		try {
			String sql = "DELETE FROM `orders` WHERE `idOrder` = ?";
			Connector con = new Connector();
			Connection cn = con.getOrderConnection();
			PreparedStatement ps = cn.prepareStatement(sql);
			ps.setLong(1, id);
			ps.executeUpdate();
			cn.close();
			ps.close();
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

	public void packOffOrder(Long id) {
		try {
			String sql = "UPDATE `orders` SET `state`= " + '"' + "despachado" + '"' + "  WHERE idOrder = ?";
			Connector con = new Connector();
			Connection cn = con.getOrderConnection();
			PreparedStatement ps = cn.prepareStatement(sql);
			ps.setLong(1, id);
			ps.executeUpdate();
			cn.close();
			ps.close();
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
	public List<Order> getOrdersByOrderId(long id){
		try {
			List<Order> oList = new ArrayList<Order>();
			Connector con = new Connector();
			Connection cn = con.getOrderConnection();
			String sql = "SELECT * FROM orders WHERE `idOrder` = ?";
			PreparedStatement ps = cn.prepareStatement(sql);
			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Order o = new Order();
				o.setClientId(rs.getInt("idClient"));
				o.setId(rs.getInt("id"));
				o.setOrderId(rs.getLong("idOrder"));
				o.setProductDescription(rs.getString("productDescription"));
				o.setProductId(rs.getInt("idProduct"));
				o.setState(rs.getString("state"));
				o.setUnitys(rs.getInt("unitys"));
				oList.add(o);
			}
			cn.close();
			ps.close();
			return oList;
		} catch (Exception ex) {
			System.out.println("Error: " + ex.getMessage());
			return null;
		}
	}

}
