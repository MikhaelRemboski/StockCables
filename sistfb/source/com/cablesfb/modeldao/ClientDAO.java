package com.cablesfb.modeldao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.cablesfb.helper.Connector;
import com.cablesfb.model.Client;

public class ClientDAO {
	public void insert(Client c) {
		try {
			Connector con = new Connector();
			Connection cn = con.getClientConnection();
			String sql = "INSERT INTO `clients`( `name`, `cuit`, `adress`, "
					+ "`email`, `transport`) VALUES ( ?,?,?,?,?)";
			PreparedStatement ps = cn.prepareStatement(sql);
			ps.setString(1, c.getName());
			ps.setLong(2, c.getCuit());
			ps.setString(3, c.getAdress());
			ps.setString(4, c.getEmail());
			ps.setString(5, c.getTransport());

			ps.executeUpdate();
			ps.close();
			cn.close();
			System.out.println("Cliente cargado con exito");

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			System.out.println("Error");

		}
	}

	public void update(Client c) {
		try {
			Connector con = new Connector();
			Connection cn = con.getClientConnection();
			String sql = "UPDATE `clients` SET `name`= ?" + ",`cuit`= ?,`adress`= ?,"
					+ "`email`= ?,`transport`= ? WHERE id = ?";
			PreparedStatement ps = cn.prepareStatement(sql);
			ps.setString(1, c.getName());
			ps.setLong(2, c.getCuit());
			ps.setString(3, c.getAdress());
			ps.setString(4, c.getEmail());
			ps.setString(5, c.getTransport());
			ps.setInt(6, c.getId());
			ps.executeUpdate();
			cn.close();
			ps.close();
			System.out.println("Cliente actualizado con exito");

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	public List<Client> selectAll() {
		try {
			List<Client> list = new ArrayList<Client>();
			Connector con = new Connector();
			Connection cn = con.getClientConnection();
			String sql = "SELECT * FROM `clients`";
			PreparedStatement ps = cn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Client c = new Client();
				c.setId(rs.getInt("id"));
				c.setCuit(rs.getLong("cuit"));
				c.setName(rs.getString("name"));
				c.setEmail(rs.getString("email"));
				c.setAdress(rs.getString("adress"));
				c.setTransport(rs.getString("transport"));
				list.add(c);
			}
			ps.close();
			cn.close();
			return list;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	public List<Client> search(String str) {
		try {
			str = "%" + str + "%";
			List<Client> list = new ArrayList<Client>();
			Connector con = new Connector();
			Connection cn = con.getClientConnection();
			String sql = "SELECT * FROM `clients` WHERE `name` LIKE ? OR `cuit` LIKE ?  OR `id` LIKE ? ORDER BY `id`  ASC";
			PreparedStatement ps = cn.prepareStatement(sql);
			ps.setString(1, str);
			ps.setString(2, str);
			ps.setString(3, str);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Client c = new Client();
				c.setId(rs.getInt("id"));
				c.setCuit(rs.getLong("cuit"));
				c.setName(rs.getString("name"));
				c.setEmail(rs.getString("email"));
				c.setAdress(rs.getString("adress"));
				c.setTransport(rs.getString("transport"));
				list.add(c);
			}
			ps.close();
			cn.close();
			return list;
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return null;
		}
	}
	public Client searchById(int id) {
		try {
			Client c = new Client();
			Connector con = new Connector();
			Connection cn = con.getClientConnection();
			String sql = "SELECT * FROM `clients` where `id`= ? ";
			PreparedStatement ps = cn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				c.setId(rs.getInt("id"));
				c.setCuit(rs.getLong("cuit"));
				c.setName(rs.getString("name"));
				c.setEmail(rs.getString("email"));
				c.setAdress(rs.getString("adress"));
				c.setTransport(rs.getString("transport"));
			}
			ps.close();
			cn.close();
			System.out.println("Encontrando cliente");
			return c;
	
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return null;
		}
		
	}
	
	public void deleteById(int id) {
		try {
			Connector con = new Connector();
			Connection cn = con.getClientConnection();
			String sql = "DELETE FROM `clients` WHERE  `id`= ? ";
			PreparedStatement ps = cn.prepareStatement(sql);
			ps.setInt(1, id);
		    ps.executeUpdate();
		    ps.close();
		    cn.close();
	}catch (Exception ex) {
		System.out.println(ex.getMessage());
	}
	}
}
