package com.cablesfb.modeldao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.cablesfb.helper.Connector;
import com.cablesfb.model.Order;


public class OrderDAO {
	public List<Order> selectAll(){
		try {
			List<Order> list = new ArrayList<Order>();
			Connector con = new Connector();
			Connection cn = con.getOrderConnection();
			String sql = "SELECT * FROM orders";
			PreparedStatement ps = cn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
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
			return list;
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return null;
	}
}
