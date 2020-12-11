package com.cablesfb.modeldao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.cablesfb.helper.Connector;
import com.cablesfb.helper.DateConverter;
import com.cablesfb.model.Registers;

public class RegistersDAO {
	public void insert(Registers r) {
		try {
			Date date = DateConverter.convertFromJAVADateToSQLDate(r.getDate());
			Connector con = new Connector();
			Connection cn = con.getLoginConnection();
			String sql = "INSERT INTO `registers`(`description`, `user`, `action`, `date`) VALUES(?,?,?,?)";
			PreparedStatement ps = cn.prepareStatement(sql);
			ps.setString(1, r.getDescription());
			ps.setString(2, r.getUser());
			ps.setString(3, r.getAction());
			ps.setDate(4, date);
			ps.executeUpdate();
			cn.close();
			ps.close();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());	
		}	
	}
	
	public List<Registers> selectByAction(String action){
		try {
			List<Registers> rList = new ArrayList<Registers>();
			Connector con = new Connector();
			Connection cn = con.getLoginConnection();
			String sql = "SELECT * FROM `registers` WHERE `action` = ?";
			PreparedStatement ps = cn.prepareStatement(sql);
			ps.setString(1, action);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Registers  r = new Registers();
				r.setAction(rs.getString("action"));
				r.setDate(rs.getDate("date"));
				r.setDescription(rs.getString("description"));
				r.setId(rs.getInt("id"));
				r.setUser(rs.getString("user"));
				rList.add(r);
			}
			ps.close();
			cn.close();
			return rList;
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return null;
		}
	}
}
